package com.enroll_web_app.enroll_web_app.servlets;

import com.enroll_web_app.enroll_web_app.xml.XMLWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch the student ID from the request parameter
        String studentId = request.getParameter("studentId");

        try {
            // Attempt to delete the student by their ID
            XMLWriter.deleteStudentById(studentId); // Call the XMLWriter to delete the student
            System.out.println("Student deleted successfully");
            request.setAttribute("success", "Student deleted successfully.");
        } catch (Exception e) {
            // Log the error and display a failure message
            e.printStackTrace();
            System.out.println("Student deletion failed");
            request.setAttribute("error", "Cannot delete this student now. Try again later.");
        }

        // Redirect to the view students page after the deletion attempt
        request.getRequestDispatcher("/viewStudents.jsp").forward(request, response);
    }
}
