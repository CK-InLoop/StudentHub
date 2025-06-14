<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edutrack.model.Marks" %>
<%@ page import="java.util.List" %>
<%
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Marks> marksList = (List<Marks>) request.getAttribute("marksList");
    String role = ((com.edutrack.model.User)userObj).getRole();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Marks - EduTrack</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>Marks</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr><th>Subject</th><th>Marks</th><th>Grade</th><th>Semester</th><c:if test="${role eq 'admin'}"><th>Actions</th></c:if></tr>
        </thead>
        <tbody>
        <c:forEach var="m" items="${marksList}">
            <tr>
                <td>${m.subject}</td>
                <td>${m.marks}</td>
                <td>${m.grade}</td>
                <td>${m.semester}</td>
                <c:if test="${role eq 'admin'}">
                    <td>
                        <form method="post" action="marks" style="display:inline;">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="id" value="${m.id}" />
                            <input type="hidden" name="studentId" value="${param.studentId}" />
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Delete marks?');">Delete</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${role eq 'admin'}">
        <form method="post" action="marks">
            <input type="hidden" name="action" value="add" />
            <input type="hidden" name="studentId" value="${param.studentId}" />
            <div class="mb-3">
                <label for="subject" class="form-label">Subject</label>
                <input type="text" class="form-control" id="subject" name="subject" required>
            </div>
            <div class="mb-3">
                <label for="marks" class="form-label">Marks</label>
                <input type="number" class="form-control" id="marks" name="marks" required>
            </div>
            <div class="mb-3">
                <label for="grade" class="form-label">Grade</label>
                <input type="text" class="form-control" id="grade" name="grade" required>
            </div>
            <div class="mb-3">
                <label for="semester" class="form-label">Semester</label>
                <input type="text" class="form-control" id="semester" name="semester" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Marks</button>
        </form>
    </c:if>
</div>
</body>
</html>
