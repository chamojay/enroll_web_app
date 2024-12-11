package com.enroll_web_app.enroll_web_app.servlets;

import com.enroll_web_app.enroll_web_app.models.Student;
import com.enroll_web_app.enroll_web_app.xml.XMLWriter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/updateStudents")
public class UpdateStudentServlet extends HttpServlet implements Servlet {

    // Logger for logging errors
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UpdateStudentServlet.class.getName());

    // Validation feedback method to reduce redundancy
    private String validateAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        // Set the error message as an attribute
        request.setAttribute("errorMessage", errorMessage);

        // Forward the request to the "updateStudent.jsp" page
        request.getRequestDispatcher("updateStudent.jsp").forward(request, response);

        // Return null to indicate no further action is needed after forwarding
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Loop through all form data and create Student objects to update
        Enumeration<String> parameterNames = request.getParameterNames();

        // Loop through form parameters to update student details
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("name_")) {  // Assuming name_ is the pattern for student form parameters
                String studentId = paramName.split("_")[1];  // Extract student ID from parameter name
                String updatedName = request.getParameter("name_" + studentId);
                String updatedEmail = request.getParameter("email_" + studentId);
                String updatedPhone = request.getParameter("phone_" + studentId);
                String updatedCourse = request.getParameter("course_" + studentId);

                // Input validations
                if (updatedName == null || updatedName.trim().isEmpty()) {
                    validateAndForward(request, response, "Student name is required.");
                    return;
                }

                if (updatedEmail == null || updatedEmail.trim().isEmpty()) {
                    validateAndForward(request, response, "Email is required.");
                    return;
                }

                if (!updatedEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {  // Basic email validation
                    validateAndForward(request, response, "Invalid email format.");
                    return;
                }

                if (updatedPhone == null || updatedPhone.trim().isEmpty()) {
                    validateAndForward(request, response, "Phone number is required.");
                    return;
                }

                // Validate phone number format (basic check, adjust as needed)
                if (!updatedPhone.matches("\\d{10}")) {
                    validateAndForward(request, response, "Phone number must be 10 digits.");
                    return;
                }

                if (updatedCourse == null || updatedCourse.trim().isEmpty()) {
                    validateAndForward(request, response, "Course name is required.");
                    return;
                }

                // If validation passes, update the student
                Student studentToUpdate = new Student();
                studentToUpdate.setId(studentId);
                studentToUpdate.setName(updatedName);
                studentToUpdate.setEmail(updatedEmail);
                studentToUpdate.setPhone(updatedPhone);
                studentToUpdate.setCourse(updatedCourse);

                try {
                    XMLWriter.updateStudent(studentToUpdate);  // Update student in the XML
                } catch (Exception e) {
                    logger.severe("Error while updating student: " + e.getMessage());
                    validateAndForward(request, response, "Cannot update student now. Please try again.");
                    return;
                }
            }
        }

        // Redirect to the viewStudents.jsp page after successful update
        response.sendRedirect("viewStudents.jsp");
    }
}
