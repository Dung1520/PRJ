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
import model.Task;

/**
 *
 * @author ADMIN
 */
public class TaskDAO extends DBContext {

    // Get all tasks by list_id
    public List<Task> getTasksByListId(int listId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task WHERE list_id = ? ORDER BY task_id DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, listId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("description"),
                        rs.getTimestamp("from_date"),
                        rs.getTimestamp("due_date"),
                        rs.getInt("list_id"),
                        rs.getBoolean("isCompleted")
                );
                tasks.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return tasks;
    }

    // Get task by task_id
    public Task getTaskById(int taskId) {
        String query = "SELECT * FROM task WHERE task_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("description"),
                        rs.getTimestamp("from_date"),
                        rs.getTimestamp("due_date"),
                        rs.getInt("list_id"),
                        rs.getBoolean("isCompleted")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    // Create new task
    public boolean createTask(String taskName, String description, Timestamp fromDate, Timestamp dueDate, int listId) {
        String query = "INSERT INTO task (task_name, description, from_date, due_date, list_id, isCompleted) VALUES (?, ?, ?, ?, ?, 0)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, taskName);
            ps.setString(2, description);
            ps.setTimestamp(3, fromDate);
            ps.setTimestamp(4, dueDate);
            ps.setInt(5, listId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Update task
    public boolean updateTask(int taskId, String taskName, String description, Timestamp fromDate, Timestamp dueDate) {
        String query = "UPDATE task SET task_name = ?, description = ?, from_date = ?, due_date = ? WHERE task_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, taskName);
            ps.setString(2, description);
            ps.setTimestamp(3, fromDate);
            ps.setTimestamp(4, dueDate);
            ps.setInt(5, taskId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Toggle task completion status
    public boolean toggleTaskCompletion(int taskId, boolean isCompleted) {
        String query = "UPDATE task SET isCompleted = ? WHERE task_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBoolean(1, isCompleted);
            ps.setInt(2, taskId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Delete task
    public boolean deleteTask(int taskId) {
        String query = "DELETE FROM task WHERE task_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, taskId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

