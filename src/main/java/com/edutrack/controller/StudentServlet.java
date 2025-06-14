package com.edutrack.controller;

import com.edutrack.dao.StudentDAO;
import com.edutrack.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/students")
@MultipartConfig
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "add";
        switch (action) {
            case "add":
                addStudent(request, response);
                break;
            case "update":
                updateStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String classVal = request.getParameter("class");
        String sectionVal = request.getParameter("section");
        List<Student> students = studentDAO.searchStudents(keyword, classVal, sectionVal);
        request.setAttribute("students", students);
        request.getRequestDispatcher("students.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = extractStudentFromRequest(request);
        studentDAO.addStudent(student);
        response.sendRedirect("students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = extractStudentFromRequest(request);
        student.setId(Integer.parseInt(request.getParameter("id")));
        studentDAO.updateStudent(student);
        response.sendRedirect("students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }

    private Student extractStudentFromRequest(HttpServletRequest request) throws IOException, ServletException {
        String name = request.getParameter("name");
        String rollNo = request.getParameter("rollNo");
        String studentClass = request.getParameter("class");
        String section = request.getParameter("section");
        String dobStr = request.getParameter("dob");
        String email = request.getParameter("email");
        Part photoPart = request.getPart("photo");
        String photo = null;
        if (photoPart != null && photoPart.getSize() > 0) {
            photo = photoPart.getSubmittedFileName();
            // Optionally, save file to disk here
        }
        Date dob = null;
        if (dobStr != null && !dobStr.isEmpty()) {
            dob = Date.valueOf(dobStr);
        }
        return new Student(0, name, rollNo, studentClass, section, dob, email, photo);
    }
}
