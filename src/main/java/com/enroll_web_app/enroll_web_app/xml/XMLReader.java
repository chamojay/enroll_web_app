package com.enroll_web_app.enroll_web_app.xml;

import com.enroll_web_app.enroll_web_app.models.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class XMLReader {

    // Read students from the XML file
    public static List<Student> readStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        File xmlFile = new File("students.xml"); // Fixed file path

        if (!xmlFile.exists() || xmlFile.length() == 0) {
            return students; // Return an empty list if the XML file doesn't exist or is empty
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList studentNodes = doc.getElementsByTagName("student");

        for (int i = 0; i < studentNodes.getLength(); i++) {
            Element studentElement = (Element) studentNodes.item(i);

            String idStr = studentElement.getAttribute("id");
            String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
            String email = studentElement.getElementsByTagName("email").item(0).getTextContent();
            String phone = studentElement.getElementsByTagName("phone").item(0).getTextContent();
            String course = studentElement.getElementsByTagName("course").item(0).getTextContent();
            String studentId = studentElement.getElementsByTagName("studentId").item(0).getTextContent();

            // Create a new Student object and set properties
            Student student = new Student(name, email, phone, course, studentId);
            students.add(student);
        }

        return students;
    }

    // Get a student by their ID
    public static Student getStudentById(Long studentId) throws Exception {
        List<Student> students = readStudents();

        for (Student student : students) {
            if (student.getId().equals(String.valueOf(studentId))) {
                return student;
            }
        }
        return null; // Return null if no student found with the given ID
    }

    // Read all students and return as a string (for debugging or viewing)
    public static String readStudentsAsString() throws Exception {
        List<Student> students = readStudents();
        StringBuilder sb = new StringBuilder();

        for (Student student : students) {
            sb.append("Student ID: ").append(student.getId()).append("\n");
            sb.append("Name: ").append(student.getName()).append("\n");
            sb.append("Email: ").append(student.getEmail()).append("\n");
            sb.append("Phone: ").append(student.getPhone()).append("\n");
            sb.append("Course: ").append(student.getCourse()).append("\n");
            sb.append("Student ID Number: ").append(student.getStudentId()).append("\n");
        }
        return sb.toString();
    }

    // Sort students by a specific field (name, email, etc.)
    public static List<Student> sortStudents(String sortField) throws Exception {
        List<Student> students = readStudents();

        switch (sortField.toLowerCase()) {
            case "name":
                return students.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList());
            case "email":
                return students.stream().sorted(Comparator.comparing(Student::getEmail)).collect(Collectors.toList());
            case "course":
                return students.stream().sorted(Comparator.comparing(Student::getCourse)).collect(Collectors.toList());
            case "phone":
                return students.stream().sorted(Comparator.comparing(Student::getPhone)).collect(Collectors.toList());
            case "studentid":
                return students.stream().sorted(Comparator.comparing(Student::getStudentId)).collect(Collectors.toList());
            default:
                return students; // If the field is unknown, return unsorted
        }
    }
}
