/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Tasks;
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
public class TaskRepository extends DBContext {
    
    // Get all tasks by list ID
    public List<Tasks> getTasksByListId(int listId) {
        List<Tasks> lst = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE list_id = ? ORDER BY id DESC";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, listId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Tasks task = new Tasks(
                    rs.getInt("id"),
                    rs.getInt("list_id"),
                    rs.getString("task_name"),
                    rs.getString("description"),
                    rs.getDate("due_date"),
                    rs.getBoolean("is_completed")
                );
                lst.add(task);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lst;
    }
    
    // Get task by task ID
    public Tasks getTaskById(int taskId) {
        String query = "SELECT * FROM tasks WHERE id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, taskId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new Tasks(
                    rs.getInt("id"),
                    rs.getInt("list_id"),
                    rs.getString("task_name"),
                    rs.getString("description"),
                    rs.getDate("due_date"),
                    rs.getBoolean("is_completed")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }
    
    // Create new task
    public boolean createTask(int listId, String taskName, String description, Date dueDate) {
        String query = "INSERT INTO tasks (list_id, task_name, description, due_date, is_completed) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, listId);
            p.setString(2, taskName);
            p.setString(3, description);
            if (dueDate != null) {
                p.setDate(4, dueDate);
            } else {
                p.setNull(4, java.sql.Types.DATE);
            }
            p.setBoolean(5, false);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Update task
    public boolean updateTask(int taskId, String taskName, String description, Date dueDate, boolean isCompleted) {
        String query = "UPDATE tasks SET task_name = ?, description = ?, due_date = ?, is_completed = ? WHERE id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setString(1, taskName);
            p.setString(2, description);
            if (dueDate != null) {
                p.setDate(3, dueDate);
            } else {
                p.setNull(3, java.sql.Types.DATE);
            }
            p.setBoolean(4, isCompleted);
            p.setInt(5, taskId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Toggle task completion status
    public boolean toggleTaskCompletion(int taskId, boolean isCompleted) {
        String query = "UPDATE tasks SET is_completed = ? WHERE id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setBoolean(1, isCompleted);
            p.setInt(2, taskId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Delete task
    public boolean deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, taskId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

