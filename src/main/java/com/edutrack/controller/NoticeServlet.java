package com.edutrack.controller;

import com.edutrack.dao.NoticeDAO;
import com.edutrack.model.Notice;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/notices")
public class NoticeServlet extends HttpServlet {
    private NoticeDAO noticeDAO = new NoticeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Notice> notices = noticeDAO.getAllNotices();
        request.setAttribute("notices", notices);
        request.getRequestDispatcher("notices.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        Notice notice = new Notice(0, title, message, null);
        noticeDAO.addNotice(notice);
        response.sendRedirect("notices");
    }
}
