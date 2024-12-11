package com.enroll_web_app.enroll_web_app.servlets;

import com.enroll_web_app.enroll_web_app.models.Student;
import com.enroll_web_app.enroll_web_app.xml.XMLReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewStudents")
public class ViewStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve the list of students from the XML (or your data source)
            List<Student> students = XMLReader.readStudents(); // Assuming XMLReader is responsible for reading student data

            // Set the list as a request attribute
            request.setAttribute("students", students);

            // Forward the student data to the viewStudents.jsp page for display
            request.getRequestDispatcher("viewStudents.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // If an error occurs, send an internal server error response
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to view students");
        }
    }
}
