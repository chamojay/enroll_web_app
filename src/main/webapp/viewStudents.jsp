<%@ page import="com.enroll_web_app.enroll_web_app.xml.XMLReader" %>
<%@ page import="com.enroll_web_app.enroll_web_app.models.Student" %>
<%@ page import="java.util.List" %>
<%@ include file="navigation.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>View Students</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">

  <%-- Display success message if present --%>
  <% String successMessage = (String) request.getAttribute("successMessage"); %>
  <% if (successMessage != null) { %>
  <div class="alert alert-success" role="alert">
    <%= successMessage %>
  </div>
  <% } %>

  <%-- Display error message if present --%>
  <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
  <% if (errorMessage != null) { %>
  <div class="alert alert-danger" role="alert">
    <%= errorMessage %>
  </div>
  <% } %>

  <h2 class="text-center mb-4">View Students</h2>
  <%
    List<Student> students = (List<Student>) request.getAttribute("students"); // Get students from request
    if (students == null || students.isEmpty()) {
  %>

  <div class="alert alert-warning text-center" role="alert">
    No students found.
  </div>
  <%
  } else {
  %>
  <table class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Course</th>
      <th>StudentId</th>
      <th>Phone No</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
      // Loop through the list of students and display each one
      for (Student student : students) {
    %>
    <tr>
      <td><%= student.getId() %></td>
      <td><%= student.getName() %></td>
      <td><%= student.getEmail() %></td>
      <td><%= student.getCourse() %></td>
      <td><%= student.getStudentId() %></td>
      <td><%= student.getPhone() %></td>

      <td>
        <form action="deleteStudent" method="post" style="display:inline;">
          <input type="hidden" name="studentId" value="<%= student.getId() %>">
          <button type="submit" class="btn btn-danger btn-sm">Delete</button>
        </form>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <%
    }
  %>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
