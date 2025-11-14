/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Lists;
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
public class ListRepository extends DBContext {
    
    // Get all lists by user ID
    public List<Lists> getListsByUserId(int userId) {
        List<Lists> lst = new ArrayList<>();
        String query = "SELECT * FROM lists WHERE user_id = ? ORDER BY list_id DESC";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Lists list = new Lists(
                    rs.getInt("list_id"),
                    rs.getInt("user_id"),
                    rs.getString("list_name")
                );
                lst.add(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lst;
    }
    
    // Get list by list ID
    public Lists getListById(int listId) {
        String query = "SELECT * FROM lists WHERE list_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, listId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new Lists(
                    rs.getInt("list_id"),
                    rs.getInt("user_id"),
                    rs.getString("list_name")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }
    
    // Create new list
    public boolean createList(int userId, String listName) {
        String query = "INSERT INTO lists (user_id, list_name) VALUES (?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            p.setString(2, listName);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ListRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Update list
    public boolean updateList(int listId, String listName) {
        String query = "UPDATE lists SET list_name = ? WHERE list_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setString(1, listName);
            p.setInt(2, listId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ListRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Delete list
    public boolean deleteList(int listId) {
        String query = "DELETE FROM lists WHERE list_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, listId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ListRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

