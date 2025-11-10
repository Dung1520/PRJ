/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.PomodoroRecord;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PomodoroRecordRepository extends DBContext {
    
    // Get all pomodoro records by user ID
    public List<PomodoroRecord> getPomodoroRecordsByUserId(int userId) {
        List<PomodoroRecord> lst = new ArrayList<>();
        String query = "SELECT * FROM pomodoro_record WHERE user_id = ? ORDER BY record_date DESC, record_id DESC";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                PomodoroRecord record = new PomodoroRecord(
                    rs.getInt("record_id"),
                    rs.getInt("user_id"),
                    rs.getInt("focus_time"),
                    rs.getDate("record_date")
                );
                lst.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroRecordRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lst;
    }
    
    // Get pomodoro records by user ID and date range
    public List<PomodoroRecord> getPomodoroRecordsByUserIdAndDateRange(int userId, Date startDate, Date endDate) {
        List<PomodoroRecord> lst = new ArrayList<>();
        String query = "SELECT * FROM pomodoro_record WHERE user_id = ? AND record_date BETWEEN ? AND ? ORDER BY record_date DESC";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            p.setDate(2, startDate);
            p.setDate(3, endDate);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                PomodoroRecord record = new PomodoroRecord(
                    rs.getInt("record_id"),
                    rs.getInt("user_id"),
                    rs.getInt("focus_time"),
                    rs.getDate("record_date")
                );
                lst.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroRecordRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lst;
    }
    
    // Get total focus time by user ID
    public int getTotalFocusTimeByUserId(int userId) {
        String query = "SELECT SUM(focus_time) as total FROM pomodoro_record WHERE user_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroRecordRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return 0;
    }
    
    // Create new pomodoro record
    public boolean createPomodoroRecord(int userId, int focusTime, Date recordDate) {
        String query = "INSERT INTO pomodoro_record (user_id, focus_time, record_date) VALUES (?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            p.setInt(2, focusTime);
            if (recordDate != null) {
                p.setDate(3, recordDate);
            } else {
                p.setDate(3, new Date(System.currentTimeMillis()));
            }
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroRecordRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Delete pomodoro record
    public boolean deletePomodoroRecord(int recordId) {
        String query = "DELETE FROM pomodoro_record WHERE record_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, recordId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PomodoroRecordRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

