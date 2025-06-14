package com.edutrack.dao;

import com.edutrack.model.Student;
import com.edutrack.util.DBConnection;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("roll_no"),
                    rs.getString("class"),
                    rs.getString("section"),
                    rs.getDate("dob"),
                    rs.getString("email"),
                    rs.getString("photo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("class"),
                        rs.getString("section"),
                        rs.getDate("dob"),
                        rs.getString("email"),
                        rs.getString("photo")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, roll_no, class, section, dob, email, photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNo());
            ps.setString(3, student.getStudentClass());
            ps.setString(4, student.getSection());
            ps.setDate(5, student.getDob());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getPhoto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, roll_no=?, class=?, section=?, dob=?, email=?, photo=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNo());
            ps.setString(3, student.getStudentClass());
            ps.setString(4, student.getSection());
            ps.setDate(5, student.getDob());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getPhoto());
            ps.setInt(8, student.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> searchStudents(String keyword, String classVal, String sectionVal) {
        List<Student> students = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM students WHERE 1=1");
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND name LIKE ?");
        }
        if (classVal != null && !classVal.isEmpty()) {
            sql.append(" AND class = ?");
        }
        if (sectionVal != null && !sectionVal.isEmpty()) {
            sql.append(" AND section = ?");
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(idx++, "%" + keyword + "%");
            }
            if (classVal != null && !classVal.isEmpty()) {
                ps.setString(idx++, classVal);
            }
            if (sectionVal != null && !sectionVal.isEmpty()) {
                ps.setString(idx++, sectionVal);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("class"),
                        rs.getString("section"),
                        rs.getDate("dob"),
                        rs.getString("email"),
                        rs.getString("photo")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
