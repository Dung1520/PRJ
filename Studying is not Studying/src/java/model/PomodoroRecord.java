/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class PomodoroRecord {
    private int recordId;
    private int time;
    private int userId;
    private Timestamp recordDate;

    public PomodoroRecord() {
    }

    public PomodoroRecord(int recordId, int time, int userId, Timestamp recordDate) {
        this.recordId = recordId;
        this.time = time;
        this.userId = userId;
        this.recordDate = recordDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "PomodoroRecord{" + "recordId=" + recordId + ", time=" + time + ", userId=" + userId + ", recordDate=" + recordDate + '}';
    }
}

