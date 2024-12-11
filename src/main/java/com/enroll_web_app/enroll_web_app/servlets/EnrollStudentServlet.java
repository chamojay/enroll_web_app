package com.enroll_web_app.enroll_web_app.servlets;

import com.enroll_web_app.enroll_web_app.xml.XMLWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/enrollStudent")
public class EnrollStudentServlet extends HttpServlet {

    // Logger for logging errors
    private static final Logger logger = Logger.getLogger(EnrollStudentServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get student details from the form
        String studentName = request.getParameter("name");
        String studentEmail = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String studentId = request.getParameter("studentId");
        String course = request.getParameter("course");




        // Validate inputs
        if (studentName == null || studentName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Student name is required.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

        if (studentId == null || studentId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Student ID is required.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

        if (course == null || course.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Course is required.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

        if (studentEmail == null || studentEmail.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email is required.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if (!studentEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("errorMessage", "Please enter a valid email address.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

        // Validate phone number
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Phone number is required.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

// Check if phone number contains exactly 10 digits
        if (!phoneNumber.matches("\\d{10}")) {
            request.setAttribute("errorMessage", "Phone number must be a 10-digit number.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
            return;
        }

        try {
            // Save student details to XML
            XMLWriter.saveStudent( studentName, studentEmail, phoneNumber, course, studentId);
            request.setAttribute("successMessage", "Student enrolled successfully.");
            request.getRequestDispatcher("viewStudents.jsp").forward(request, response);
        } catch (Exception e) {
            // Log the exception for debugging
            logger.log(Level.SEVERE, "Error while enrolling the student: ", e);
            request.setAttribute("errorMessage", "Unable to enroll the student at this time. Please try again later.");
            request.getRequestDispatcher("enrollStudent.jsp").forward(request, response);
        }
    }
}
