<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edutrack.model.Student" %>
<%
    Object userObj = session.getAttribute("user");
    if (userObj == null || !"admin".equals(((com.edutrack.model.User)userObj).getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
    Student student = (Student) request.getAttribute("student");
    boolean isEdit = student != null;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Edit Student" : "Add Student" %> - EduTrack</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3><%= isEdit ? "Edit Student" : "Add Student" %></h3>
    <form method="post" action="students" enctype="multipart/form-data">
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "add" %>" />
        <c:if test="${isEdit}">
            <input type="hidden" name="id" value="${student.id}" />
        </c:if>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${student.name}" required>
        </div>
        <div class="mb-3">
            <label for="rollNo" class="form-label">Roll No</label>
            <input type="text" class="form-control" id="rollNo" name="rollNo" value="${student.rollNo}" required>
        </div>
        <div class="mb-3">
            <label for="class" class="form-label">Class</label>
            <input type="text" class="form-control" id="class" name="class" value="${student.studentClass}" required>
        </div>
        <div class="mb-3">
            <label for="section" class="form-label">Section</label>
            <input type="text" class="form-control" id="section" name="section" value="${student.section}" required>
        </div>
        <div class="mb-3">
            <label for="dob" class="form-label">Date of Birth</label>
            <input type="date" class="form-control" id="dob" name="dob" value="${student.dob}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${student.email}">
        </div>
        <div class="mb-3">
            <label for="photo" class="form-label">Photo</label>
            <input type="file" class="form-control" id="photo" name="photo">
            <c:if test="${not empty student.photo}">
                <img src="uploads/${student.photo}" width="80"/>
            </c:if>
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
        <a href="students" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
