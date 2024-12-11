package com.enroll_web_app.enroll_web_app.models;

public class Student {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String course;
    private String studentId;

    // Constructor updated to accept a String for id
    public Student(String name, String email, String phone, String course, String studentId) {
        this.id = id; // Convert id from String to long
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.studentId = studentId;
    }

    // Default constructor
    public Student() {
    }

    // Getter for id as a String
    public String getId() {
        return String.valueOf(id);
    }

    // Setter for id with String input
    public void setId(String id) {
        this.id = Long.parseLong(id); // Convert id from String to long
    }

    // Setter for id with long input
    public void setId(long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for course
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Getter and Setter for course
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
