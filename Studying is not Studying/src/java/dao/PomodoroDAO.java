/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PomodoroRecord;

/**
 *
 * @author ADMIN
 */
public class PomodoroDAO extends DBContext {

    // Get all pomodoro records by user_id
    public List<PomodoroRecord> getPomodoroRecordsByUserId(int userId) {
        List<PomodoroRecord> records = new ArrayList<>();
        String query = "SELECT * FROM pomodoro_record WHERE user_id = ? ORDER BY record_date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PomodoroRecord record = new PomodoroRecord(
                        rs.getInt("record_id"),
                        rs.getInt("time"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("record_date")
                );
                records.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return records;
    }

    // Get pomodoro records by date range
    public List<PomodoroRecord> getPomodoroRecordsByDateRange(int userId, Timestamp startDate, Timestamp endDate) {
        List<PomodoroRecord> records = new ArrayList<>();
        String query = "SELECT * FROM pomodoro_record WHERE user_id = ? AND record_date BETWEEN ? AND ? ORDER BY record_date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setTimestamp(2, startDate);
            ps.setTimestamp(3, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PomodoroRecord record = new PomodoroRecord(
                        rs.getInt("record_id"),
                        rs.getInt("time"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("record_date")
                );
                records.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return records;
    }

    // Create new pomodoro record
    public boolean createPomodoroRecord(int time, int userId) {
        String query = "INSERT INTO pomodoro_record (time, user_id, record_date) VALUES (?, ?, GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, time);
            ps.setInt(2, userId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Get total time studied by user
    public int getTotalTimeByUserId(int userId) {
        String query = "SELECT SUM(time) as total_time FROM pomodoro_record WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_time");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return 0;
    }

    // Delete pomodoro record
    public boolean deletePomodoroRecord(int recordId) {
        String query = "DELETE FROM pomodoro_record WHERE record_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, recordId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

