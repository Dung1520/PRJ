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
public class Task {
    private int taskId;
    private String taskName;
    private String description;
    private Timestamp fromDate;
    private Timestamp dueDate;
    private int listId;
    private boolean isCompleted;

    public Task() {
    }

    public Task(int taskId, String taskName, String description, Timestamp fromDate, Timestamp dueDate, int listId, boolean isCompleted) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.fromDate = fromDate;
        this.dueDate = dueDate;
        this.listId = listId;
        this.isCompleted = isCompleted;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", taskName=" + taskName + ", description=" + description + ", fromDate=" + fromDate + ", dueDate=" + dueDate + ", listId=" + listId + ", isCompleted=" + isCompleted + '}';
    }
}

