<%@ page import="com.enroll_web_app.enroll_web_app.xml.XMLReader" %>
<%@ page import="com.enroll_web_app.enroll_web_app.models.Student" %>
<%@ page import="java.util.List" %>
<%@ include file="navigation.jsp" %>  <!-- Including navigation bar or menu -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Students</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <!-- Page Heading -->
    <h2 class="mt-4">Search Students</h2>

    <!-- Display error message if present -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <div class="alert alert-danger">
        <%= errorMessage %>  <!-- Display the error message -->
    </div>
    <% } %>

    <!-- Search Form -->
    <div class="form-container">
        <form action="searchStudents" method="get">  <!-- Form to submit the search criteria -->

            <!-- Student Name field -->
            <div class="form-group">
                <label for="keyword">Student Name</label>
                <input type="text" class="form-control" id="keyword" name="keyword" placeholder="Search by student name">
            </div>

            <!-- Student ID field -->
            <div class="form-group">
                <label for="filterID">Student ID</label>
                <input type="text" class="form-control" id="filterID" name="filterID" placeholder="Search by student ID">
            </div>


            <!-- Sort By field (Options: Name, Course, Student ID) -->
            <div class="form-group">
                <label for="sortField">Sort By</label>
                <select class="form-control" id="sortField" name="sortField">
                    <option value="name">Name</option>
                    <option value="course">Course</option>
                    <option value="id">Student ID</option>
                </select>
            </div>

            <!-- Sort Order field (Options: Ascending, Descending) -->
            <div class="form-group">
                <label for="sortOrder">Order</label>
                <select class="form-control" id="sortOrder" name="sortOrder">
                    <option value="asc">Ascending</option>
                    <option value="desc">Descending</option>
                </select>
            </div>

            <!-- Search button -->
            <button type="submit" class="btn btn-dark btn-block">Search</button>
        </form>
    </div>

    <!-- Display Search Results if available -->
    <%
        List<Student> searchResults = (List<Student>) request.getAttribute("searchResults");
        if (searchResults != null && !searchResults.isEmpty()) {
    %>
    <!-- Table to display search results -->
    <table class="table table-striped table-bordered mt-4">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Course</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Student student : searchResults) {  // Loop through the search results and display each student
        %>
        <tr>
            <td><%= student.getId() %></td>  <!-- Display Student ID -->
            <td><%= student.getName() %></td>  <!-- Display Student Name -->
            <td><%= student.getEmail() %></td>  <!-- Display Email -->
            <td><%= student.getPhone() %></td>  <!-- Display Phone -->
            <td><%= student.getCourse() %></td>  <!-- Display Course -->
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <% } else { %>
    <!-- Display message if no students are found -->
    <div class="alert alert-warning mt-4">
        Could not find any student.
    </div>
    <% } %>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
