StudentHub - Java Web Student Management System
=========================================================

Stack:
- Backend: Jakarta EE 11 (Servlets, JSP, JDBC, MVC)
- Server: Apache Tomcat 11
- Frontend: Bootstrap 5
- Database: MySQL
- Build: Standard Java Web Project (no Maven)

Directory Structure:
---------------------
- src/main/java/com/edutrack/
    - controller/ (Servlets)
    - dao/ (DAOs)
    - model/ (POJOs)
    - util/ (DBConnection)
- webapp/
    - *.jsp (JSP views)
    - WEB-INF/web.xml
    - uploads/ (for student photos)
- mysql_schema.sql (DB schema & sample data)

Deployment Steps:
-----------------
1. Import `mysql_schema.sql` into your MySQL server.
2. Open the project in NetBeans IDE 25.
3. Make sure to add the MySQL JDBC driver to your project libraries.
4. Build and deploy the project to Apache Tomcat 11.
5. Access the app at http://localhost:8080/StudentHub/

Default Users:
--------------
- Admin: username: admin, password: admin123
- Student: username: student1, password: student123

Features:
---------
- Admin & Student login (role-based dashboard)
- Add, edit, delete, view students
- Manage marks and attendance
- Notices (admin posts, all see)
- Bootstrap 5 UI

Security:
---------
- Passwords are stored as plaintext for demo. Extend UserDAO for hashing in production.
- Session-based authentication. Use logout.jsp to end session.

Notes:
------
- To enable photo uploads, create a `uploads/` folder under `webapp/` and configure file saving in StudentServlet.
- JSTL must be included in your server libraries.
- For production, improve password security and input validation.

Contact:
--------
For help, contact your developer.

Enjoy using StudentHub!
