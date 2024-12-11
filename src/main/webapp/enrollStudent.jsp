<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navigation.jsp" %>  <!-- Including navigation bar or menu -->
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Enroll Student</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">

  <!-- Display error message if available -->
  <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
  <% if (errorMessage != null) { %>
  <div class="alert alert-danger" role="alert">
    <%= errorMessage %>
  </div>
  <% } %>

  <!-- Display success message if available -->
  <% String successMessage = (String) request.getAttribute("successMessage"); %>
  <% if (successMessage != null) { %>
  <div class="alert alert-success" role="alert">
    <%= successMessage %>
  </div>
  <% } %>

  <!-- Page title -->
  <h1 class="text-center mb-4">Enroll a New Student</h1>

  <!-- Student enrollment form -->
  <form action="enrollStudent" method="post" class="border p-4 shadow-sm rounded bg-light">

    <!-- Student Name field -->
    <div class="form-group">
      <label for="name">Student Name:</label>
      <input type="text" id="name" name="name" class="form-control" required>
    </div>

    <!-- Student Email field -->
    <div class="form-group">
      <label for="email">Email Address:</label>
      <input type="email" id="email" name="email" class="form-control" required>
    </div>

    <!-- Student Phone Number field -->
    <div class="form-group">
      <label for="phone">Phone Number:</label>
      <input type="text" id="phone" name="phone" class="form-control" required>
    </div>

    <!-- Student ID field -->
    <div class="form-group">
      <label for="studentId">Student ID:</label>
      <input type="text" id="studentId" name="studentId" class="form-control" required>
    </div>

    <!-- Course selection dropdown -->
    <div class="form-group">
      <label for="course">Course:</label>
      <select id="course" name="course" class="form-control" required>
        <option value="">Select a Course</option>
        <option value="Bachelors in Computer Science">Bachelors in Computer Science</option>
        <option value="Bachelors in Information Systems">Bachelors in Information Systems</option>
        <option value="Masters in Business Administration">Masters in Business Administration</option>
        <!-- Add more course options as needed -->
      </select>
    </div>

    <!-- Submit button -->
    <button type="submit" class="btn btn-dark btn-block">Enroll Student</button>
  </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
