<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edutrack.model.Attendance" %>
<%@ page import="java.util.List" %>
<%
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceList");
    String role = ((com.edutrack.model.User)userObj).getRole();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Attendance - EduTrack</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>Attendance</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr><th>Date</th><th>Status</th></tr>
        </thead>
        <tbody>
        <c:forEach var="att" items="${attendanceList}">
            <tr>
                <td>${att.date}</td>
                <td>${att.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${role eq 'admin'}">
        <form method="post" action="attendance">
            <input type="hidden" name="studentId" value="${param.studentId}" />
            <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select class="form-control" id="status" name="status">
                    <option value="Present">Present</option>
                    <option value="Absent">Absent</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Attendance</button>
        </form>
    </c:if>
</div>
</body>
</html>
