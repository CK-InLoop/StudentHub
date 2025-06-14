package com.edutrack.controller;

import com.edutrack.dao.AttendanceDAO;
import com.edutrack.model.Attendance;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        List<Attendance> list = attendanceDAO.getAttendanceByStudent(studentId);
        request.setAttribute("attendanceList", list);
        request.getRequestDispatcher("attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Date date = Date.valueOf(request.getParameter("date"));
        String status = request.getParameter("status");
        Attendance att = new Attendance(0, studentId, date, status);
        attendanceDAO.addAttendance(att);
        response.sendRedirect("attendance?studentId=" + studentId);
    }
}
