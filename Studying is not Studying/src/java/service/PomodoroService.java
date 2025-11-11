/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PomodoroDAO;
import java.sql.Timestamp;
import java.util.List;
import model.PomodoroRecord;

/**
 *
 * @author ADMIN
 */
public class PomodoroService {
    private PomodoroDAO pomodoroDAO = new PomodoroDAO();

    public List<PomodoroRecord> getPomodoroRecordsByUserId(int userId) {
        return pomodoroDAO.getPomodoroRecordsByUserId(userId);
    }

    public List<PomodoroRecord> getPomodoroRecordsByDateRange(int userId, Timestamp startDate, Timestamp endDate) {
        return pomodoroDAO.getPomodoroRecordsByDateRange(userId, startDate, endDate);
    }

    public boolean createPomodoroRecord(int time, int userId) {
        if (time <= 0) {
            return false;
        }
        return pomodoroDAO.createPomodoroRecord(time, userId);
    }

    public int getTotalTimeByUserId(int userId) {
        return pomodoroDAO.getTotalTimeByUserId(userId);
    }

    public boolean deletePomodoroRecord(int recordId) {
        return pomodoroDAO.deletePomodoroRecord(recordId);
    }
}

