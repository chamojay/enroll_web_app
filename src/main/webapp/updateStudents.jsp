<%@ page import="com.enroll_web_app.enroll_web_app.xml.XMLReader" %>
<%@ page import="com.enroll_web_app.enroll_web_app.models.Student" %>
<%@ page import="java.util.List" %>
<%@ include file="navigation.jsp" %>

<html>
<body>
<div class="container mt-4">

    <%-- Display error message --%>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%= errorMessage %>  <!-- Display error message -->
    </div>
    <% } %>

    <h2 class="text-center mb-4">Update Students</h2>

    <%
        // Read the list of students (assumed method readStudents() returns a list of Student objects)
        List<Student> students = XMLReader.readStudents();
        if (students.isEmpty()) {
    %>
    <p>No students to update.</p>
    <%
    } else {
    %>
    <form action="update" method="post">
        <table class="table table-striped table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Course</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            <%
                // Loop through the list of students to populate the form fields
                for (Student student : students) {
            %>
            <tr>
                <td><%= student.getId() %></td>  <!-- Display student ID -->
                <td><input type="text" name="name_<%= student.getId() %>" value="<%= student.getName() %>"></td>  <!-- Input field for student name -->
                <td><input type="text" name="course<%= student.getId() %>" value="<%= student.getCourse() %>"></td>  <!-- Input field for student course -->
                <td><input type="email" name="phone_<%= student.getId() %>" value="<%= student.getEmail() %>"></td>  <!-- Input field for student age -->
                <td><input type="text" name="email_<%= student.getId() %>" value="<%= student.get %>"></td>
                <td><input type="number" name="email_<%= student.getId() %>" value="<%= student.getPhone() %>"></td>
            </tr>
            <%
                }
            %>
        </table>
        <button type="submit" class="btn btn-dark btn-block">Update</button>  <!-- Update button -->
    </form>
    <%
        }
    %>
</div>
</body>
</html>
