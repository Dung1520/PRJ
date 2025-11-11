/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TodoList;

/**
 *
 * @author ADMIN
 */
public class TodoListDAO extends DBContext {

    // Get all todo lists by user_id
    public List<TodoList> getTodoListsByUserId(int userId) {
        List<TodoList> lists = new ArrayList<>();
        String query = "SELECT * FROM todo_list WHERE user_id = ? ORDER BY list_id DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TodoList list = new TodoList(
                        rs.getInt("list_id"),
                        rs.getInt("user_id"),
                        rs.getString("list_name")
                );
                lists.add(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TodoListDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lists;
    }

    // Get todo list by list_id
    public TodoList getTodoListById(int listId) {
        String query = "SELECT * FROM todo_list WHERE list_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, listId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TodoList(
                        rs.getInt("list_id"),
                        rs.getInt("user_id"),
                        rs.getString("list_name")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TodoListDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    // Create new todo list
    public boolean createTodoList(int userId, String listName) {
        String query = "INSERT INTO todo_list (user_id, list_name) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, listName);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TodoListDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Update todo list
    public boolean updateTodoList(int listId, String listName) {
        String query = "UPDATE todo_list SET list_name = ? WHERE list_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, listName);
            ps.setInt(2, listId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TodoListDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Delete todo list
    public boolean deleteTodoList(int listId) {
        String query = "DELETE FROM todo_list WHERE list_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, listId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TodoListDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

