/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.TaskDAO;
import java.sql.Timestamp;
import java.util.List;
import model.Task;

/**
 *
 * @author ADMIN
 */
public class TaskService {
    private TaskDAO taskDAO = new TaskDAO();

    public List<Task> getTasksByListId(int listId) {
        return taskDAO.getTasksByListId(listId);
    }

    public Task getTaskById(int taskId) {
        return taskDAO.getTaskById(taskId);
    }

    public boolean createTask(String taskName, String description, Timestamp fromDate, Timestamp dueDate, int listId) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        return taskDAO.createTask(taskName.trim(), description, fromDate, dueDate, listId);
    }

    public boolean updateTask(int taskId, String taskName, String description, Timestamp fromDate, Timestamp dueDate) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        return taskDAO.updateTask(taskId, taskName.trim(), description, fromDate, dueDate);
    }

    public boolean toggleTaskCompletion(int taskId, boolean isCompleted) {
        return taskDAO.toggleTaskCompletion(taskId, isCompleted);
    }

    public boolean deleteTask(int taskId) {
        return taskDAO.deleteTask(taskId);
    }
}

