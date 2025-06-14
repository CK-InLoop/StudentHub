package com.edutrack.dao;

import com.edutrack.model.Notice;
import com.edutrack.util.DBConnection;
import java.sql.*;
import java.util.*;

public class NoticeDAO {
    public List<Notice> getAllNotices() {
        List<Notice> notices = new ArrayList<>();
        String sql = "SELECT * FROM notices ORDER BY posted_on DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                notices.add(new Notice(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("message"),
                    rs.getTimestamp("posted_on")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notices;
    }

    public boolean addNotice(Notice notice) {
        String sql = "INSERT INTO notices (title, message) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, notice.getTitle());
            ps.setString(2, notice.getMessage());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
