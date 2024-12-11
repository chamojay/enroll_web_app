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

@WebServlet("/searchStudents")
public class SearchStudentsServlet extends HttpServlet {

    private IOException Exception;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters for search
        String keyword = request.getParameter("keyword");
        String filterCourse = request.getParameter("filterCourse");


        // Retrieve sorting parameters (e.g., sorting by name, grade)
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        // Get search results based on the provided parameters
        List<Student> searchResults = null;
        try {
            searchResults = XMLReader.searchStudents(keyword, filterCourse, sortField, sortOrder);throw (Exception);
        } catch (Exception e) {
            // Handle any exceptions and set error message attribute
            e.printStackTrace();
            request.setAttribute("errorMessage", "Something went wrong. Please try again.");
        }

        // Set the search results as request attribute and forward to JSP
        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("/searchStudents.jsp").forward(request, response);
    }
}
