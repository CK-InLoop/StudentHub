-- StudentHub MySQL Schema

CREATE DATABASE IF NOT EXISTS studenthub;
USE studenthub;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin','student') NOT NULL
);

-- Students Table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    roll_no VARCHAR(20) NOT NULL UNIQUE,
    class VARCHAR(20) NOT NULL,
    section VARCHAR(10) NOT NULL,
    dob DATE,
    email VARCHAR(100),
    photo VARCHAR(255)
);

-- Marks Table
CREATE TABLE IF NOT EXISTS marks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject VARCHAR(50),
    marks INT,
    grade VARCHAR(2),
    semester VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

-- Attendance Table
CREATE TABLE IF NOT EXISTS attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    date DATE,
    status ENUM('Present','Absent'),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

-- Notices Table
CREATE TABLE IF NOT EXISTS notices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    message TEXT,
    posted_on DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Dummy Admin User
INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'admin')
    ON DUPLICATE KEY UPDATE username=username;

-- Dummy Student User
INSERT INTO users (username, password, role) VALUES ('student1', 'student123', 'student')
    ON DUPLICATE KEY UPDATE username=username;

-- Dummy Students
INSERT INTO students (name, roll_no, class, section, dob, email) VALUES
('John Doe', 'S101', '10', 'A', '2008-05-12', 'john@example.com'),
('Jane Smith', 'S102', '10', 'B', '2008-07-21', 'jane@example.com');

-- Dummy Marks
INSERT INTO marks (student_id, subject, marks, grade, semester) VALUES
(1, 'Math', 88, 'A', 'Sem1'),
(1, 'Science', 76, 'B', 'Sem1'),
(2, 'Math', 91, 'A', 'Sem1'),
(2, 'Science', 85, 'A', 'Sem1');

-- Dummy Attendance
INSERT INTO attendance (student_id, date, status) VALUES
(1, '2025-06-10', 'Present'),
(1, '2025-06-11', 'Absent'),
(2, '2025-06-10', 'Present'),
(2, '2025-06-11', 'Present');

-- Dummy Notices
INSERT INTO notices (title, message) VALUES
('Welcome!', 'Welcome to EduTrack Student Management System!'),
('Exam Schedule', 'Semester exams will start from 1st July.');
