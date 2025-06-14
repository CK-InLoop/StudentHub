<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edutrack.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - EduTrack</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EduTrack</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><span class="nav-link">Welcome, <%= user.getUsername() %> (<%= user.getRole() %>)</span></li>
                <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-4">
    <c:choose>
        <c:when test="${user.role eq 'admin'}">
            <div class="row g-3">
                <div class="col-md-4"><a href="students" class="btn btn-outline-primary w-100">Manage Students</a></div>
                <div class="col-md-4"><a href="notices" class="btn btn-outline-secondary w-100">Manage Notices</a></div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row g-3">
                <div class="col-md-4"><a href="students" class="btn btn-outline-primary w-100">View Profile</a></div>
                <div class="col-md-4"><a href="marks?studentId=${user.id}" class="btn btn-outline-info w-100">View Marks</a></div>
                <div class="col-md-4"><a href="attendance?studentId=${user.id}" class="btn btn-outline-success w-100">View Attendance</a></div>
                <div class="col-md-4"><a href="notices" class="btn btn-outline-secondary w-100">View Notices</a></div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
