<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.edutrack.model.Notice" %>
<%@ page import="java.util.List" %>
<%
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Notice> notices = (List<Notice>) request.getAttribute("notices");
    String role = ((com.edutrack.model.User)userObj).getRole();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Notices - EduTrack</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>Notices</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr><th>Title</th><th>Message</th><th>Posted On</th></tr>
        </thead>
        <tbody>
        <c:forEach var="n" items="${notices}">
            <tr>
                <td>${n.title}</td>
                <td>${n.message}</td>
                <td>${n.postedOn}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${role eq 'admin'}">
        <form method="post" action="notices">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="message" class="form-label">Message</label>
                <textarea class="form-control" id="message" name="message" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Post Notice</button>
        </form>
    </c:if>
</div>
</body>
</html>
