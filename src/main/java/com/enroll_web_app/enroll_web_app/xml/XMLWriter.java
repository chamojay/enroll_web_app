package com.enroll_web_app.enroll_web_app.xml;

import com.enroll_web_app.enroll_web_app.models.Student;
import jakarta.servlet.ServletContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

import static com.enroll_web_app.enroll_web_app.xml.XMLReader.readStudents;

public class XMLWriter {

    // Save a new student to the XML file
    public static void saveStudent(String name, String email, String phone, String course, String studentId) throws Exception {
        File xmlFile = new File("students.xml"); // Fixed file extension
        System.out.println("Saving XML file at: " + xmlFile.getAbsolutePath());

        // Ensure the directory exists if it's in a nested structure
        File parentDir = xmlFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc;

        // Parse the existing file or create a new document
        if (xmlFile.exists() && xmlFile.length() > 0) {
            doc = dBuilder.parse(xmlFile);
        } else {
            doc = dBuilder.newDocument();
            Element root = doc.createElement("students");
            doc.appendChild(root);
        }

        // Create and append the new student element
        Element studentElement = doc.createElement("student");
        studentElement.setAttribute("id", String.valueOf(System.currentTimeMillis())); // Unique ID for the student

        Element nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(name));
        studentElement.appendChild(nameElement);

        Element emailElement = doc.createElement("email");
        emailElement.appendChild(doc.createTextNode(email));
        studentElement.appendChild(emailElement);

        Element phoneElement = doc.createElement("phone");
        phoneElement.appendChild(doc.createTextNode(phone));
        studentElement.appendChild(phoneElement);

        Element courseElement = doc.createElement("course");
        courseElement.appendChild(doc.createTextNode(course));
        studentElement.appendChild(courseElement);

        Element studentIdElement = doc.createElement("studentId");
        studentIdElement.appendChild(doc.createTextNode(studentId)); // Correctly append studentId
        studentElement.appendChild(studentIdElement);

        doc.getDocumentElement().appendChild(studentElement);

        // Write the updated document back to the file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);

        System.out.println("Student added and XML file saved successfully.");
    }

    // Update an existing student
    public static void updateStudent(Student updateStudent) throws Exception {
        File xmlFile = new File("students.xml"); // Fixed file path

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        NodeList studentNodes = doc.getElementsByTagName("student");
        for (int i = 0; i < studentNodes.getLength(); i++) {
            Element studentElement = (Element) studentNodes.item(i);
            if (studentElement.getAttribute("id").equals(updateStudent.getId())) {
                studentElement.getElementsByTagName("name").item(0).setTextContent(updateStudent.getName());
                studentElement.getElementsByTagName("email").item(0).setTextContent(updateStudent.getEmail());
                studentElement.getElementsByTagName("course").item(0).setTextContent(updateStudent.getCourse());
                studentElement.getElementsByTagName("phone").item(0).setTextContent(updateStudent.getPhone());
                studentElement.getElementsByTagName("studentId").item(0).setTextContent(updateStudent.getStudentId()); // Correct studentId update

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);
                break;
            }
        }
    }

    // Delete a student by ID
    public static void deleteStudentById(String studentId) throws Exception {
        List<Student> students = readStudents(); // Get the list of students from XML
        students.removeIf(student -> student.getId().equals(studentId)); // Remove the student by ID
        saveStudents(students); // Save the updated list back to XML
    }

    // Save the list of students to XML
    public static void saveStudents(List<Student> students) throws Exception {
        File xmlFile = new File("students.xml"); // Fixed file path

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element root = doc.createElement("students");
        doc.appendChild(root);

        for (Student student : students) {
            Element studentElement = doc.createElement("student");
            studentElement.setAttribute("id", student.getId());

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(student.getName()));
            studentElement.appendChild(nameElement);

            Element emailElement = doc.createElement("email");
            emailElement.appendChild(doc.createTextNode(student.getEmail()));
            studentElement.appendChild(emailElement);

            Element courseElement = doc.createElement("course");
            courseElement.appendChild(doc.createTextNode(student.getCourse()));
            studentElement.appendChild(courseElement);

            Element phoneElement = doc.createElement("phone");
            phoneElement.appendChild(doc.createTextNode(student.getPhone()));
            studentElement.appendChild(phoneElement);

            Element studentIdElement = doc.createElement("studentId");
            studentIdElement.appendChild(doc.createTextNode(student.getStudentId())); // Correctly append studentId
            studentElement.appendChild(studentIdElement);

            root.appendChild(studentElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);

        System.out.println("XML file updated successfully.");
    }
}
