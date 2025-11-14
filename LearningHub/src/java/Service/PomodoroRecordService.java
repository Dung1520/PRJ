/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.PomodoroRecordRepository;
import Model.PomodoroRecord;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PomodoroRecordService {
    private final PomodoroRecordRepository pomodoroRepo = new PomodoroRecordRepository();
    
    // Get all pomodoro records by user ID
    public List<PomodoroRecord> getPomodoroRecordsByUserId(int userId) {
        return pomodoroRepo.getPomodoroRecordsByUserId(userId);
    }
    
    // Get pomodoro records by user ID and date range
    public List<PomodoroRecord> getPomodoroRecordsByUserIdAndDateRange(int userId, Date startDate, Date endDate) {
        return pomodoroRepo.getPomodoroRecordsByUserIdAndDateRange(userId, startDate, endDate);
    }
    
    // Get total focus time by user ID
    public int getTotalFocusTimeByUserId(int userId) {
        return pomodoroRepo.getTotalFocusTimeByUserId(userId);
    }
    
    // Create new pomodoro record
    public boolean createPomodoroRecord(int userId, int focusTime, Date recordDate) {
        if (focusTime <= 0) {
            return false;
        }
        return pomodoroRepo.createPomodoroRecord(userId, focusTime, recordDate);
    }
    
    // Delete pomodoro record
    public boolean deletePomodoroRecord(int recordId) {
        return pomodoroRepo.deletePomodoroRecord(recordId);
    }
}
