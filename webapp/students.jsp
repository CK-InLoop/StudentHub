<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edutrack.model.Student" %>
<%@ page import="java.util.List" %>
<%
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String role = ((com.edutrack.model.User)userObj).getRole();
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students - EduTrack</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Student List</h3>
        <c:if test="${role eq 'admin'}">
            <a href="addStudent.jsp" class="btn btn-primary">Add Student</a>
        </c:if>
    </div>
    <form class="row g-3 mb-3" method="get" action="students">
        <div class="col-md-3">
            <input type="text" name="keyword" class="form-control" placeholder="Search by name">
        </div>
        <div class="col-md-2">
            <input type="text" name="class" class="form-control" placeholder="Class">
        </div>
        <div class="col-md-2">
            <input type="text" name="section" class="form-control" placeholder="Section">
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-outline-primary">Filter</button>
        </div>
    </form>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th><th>Name</th><th>Roll No</th><th>Class</th><th>Section</th><th>DOB</th><th>Email</th><th>Photo</th>
            <c:if test="${role eq 'admin'}"><th>Actions</th></c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.rollNo}</td>
                <td>${student.studentClass}</td>
                <td>${student.section}</td>
                <td>${student.dob}</td>
                <td>${student.email}</td>
                <td><c:if test="${not empty student.photo}"><img src="uploads/${student.photo}" width="40"/></c:if></td>
                <c:if test="${role eq 'admin'}">
                    <td>
                        <a href="students?action=edit&id=${student.id}" class="btn btn-sm btn-warning">Edit</a>
                        <a href="students?action=delete&id=${student.id}" class="btn btn-sm btn-danger" onclick="return confirm('Delete student?');">Delete</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
