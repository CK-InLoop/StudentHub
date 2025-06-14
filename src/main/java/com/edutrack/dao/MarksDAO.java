package com.edutrack.dao;

import com.edutrack.model.Marks;
import com.edutrack.util.DBConnection;
import java.sql.*;
import java.util.*;

public class MarksDAO {
    public List<Marks> getMarksByStudent(int studentId) {
        List<Marks> marksList = new ArrayList<>();
        String sql = "SELECT * FROM marks WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    marksList.add(new Marks(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("subject"),
                        rs.getInt("marks"),
                        rs.getString("grade"),
                        rs.getString("semester")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marksList;
    }

    public boolean addMarks(Marks marks) {
        String sql = "INSERT INTO marks (student_id, subject, marks, grade, semester) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, marks.getStudentId());
            ps.setString(2, marks.getSubject());
            ps.setInt(3, marks.getMarks());
            ps.setString(4, marks.getGrade());
            ps.setString(5, marks.getSemester());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMarks(Marks marks) {
        String sql = "UPDATE marks SET subject=?, marks=?, grade=?, semester=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, marks.getSubject());
            ps.setInt(2, marks.getMarks());
            ps.setString(3, marks.getGrade());
            ps.setString(4, marks.getSemester());
            ps.setInt(5, marks.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMarks(int id) {
        String sql = "DELETE FROM marks WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
