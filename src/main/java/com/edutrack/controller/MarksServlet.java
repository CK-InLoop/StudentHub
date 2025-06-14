package com.edutrack.controller;

import com.edutrack.dao.MarksDAO;
import com.edutrack.model.Marks;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/marks")
public class MarksServlet extends HttpServlet {
    private MarksDAO marksDAO = new MarksDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        List<Marks> marksList = marksDAO.getMarksByStudent(studentId);
        request.setAttribute("marksList", marksList);
        request.getRequestDispatcher("marks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "add";
        switch (action) {
            case "add":
                addMarks(request, response);
                break;
            case "update":
                updateMarks(request, response);
                break;
            case "delete":
                deleteMarks(request, response);
                break;
        }
    }

    private void addMarks(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String subject = request.getParameter("subject");
        int marks = Integer.parseInt(request.getParameter("marks"));
        String grade = request.getParameter("grade");
        String semester = request.getParameter("semester");
        Marks m = new Marks(0, studentId, subject, marks, grade, semester);
        marksDAO.addMarks(m);
        response.sendRedirect("marks?studentId=" + studentId);
    }

    private void updateMarks(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String subject = request.getParameter("subject");
        int marks = Integer.parseInt(request.getParameter("marks"));
        String grade = request.getParameter("grade");
        String semester = request.getParameter("semester");
        Marks m = new Marks(id, studentId, subject, marks, grade, semester);
        marksDAO.updateMarks(m);
        response.sendRedirect("marks?studentId=" + studentId);
    }

    private void deleteMarks(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        marksDAO.deleteMarks(id);
        response.sendRedirect("marks?studentId=" + studentId);
    }
}
