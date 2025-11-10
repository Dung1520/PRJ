/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Flashdecks;
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
public class FlashdeckRepository extends DBContext {
    
    // Get all flashdecks by user ID
    public List<Flashdecks> getFlashdecksByUserId(int userId) {
        List<Flashdecks> lst = new ArrayList<>();
        String query = "SELECT * FROM flashdecks WHERE user_id = ? ORDER BY deck_id DESC";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Flashdecks deck = new Flashdecks(
                    rs.getInt("deck_id"),
                    rs.getInt("user_id"),
                    rs.getString("deck_name")
                );
                lst.add(deck);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashdeckRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lst;
    }
    
    // Get flashdeck by deck ID
    public Flashdecks getFlashdeckById(int deckId) {
        String query = "SELECT * FROM flashdecks WHERE deck_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, deckId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new Flashdecks(
                    rs.getInt("deck_id"),
                    rs.getInt("user_id"),
                    rs.getString("deck_name")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashdeckRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }
    
    // Create new flashdeck
    public boolean createFlashdeck(int userId, String deckName) {
        String query = "INSERT INTO flashdecks (user_id, deck_name) VALUES (?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, userId);
            p.setString(2, deckName);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashdeckRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Update flashdeck
    public boolean updateFlashdeck(int deckId, String deckName) {
        String query = "UPDATE flashdecks SET deck_name = ? WHERE deck_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setString(1, deckName);
            p.setInt(2, deckId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashdeckRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Delete flashdeck
    public boolean deleteFlashdeck(int deckId) {
        String query = "DELETE FROM flashdecks WHERE deck_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, deckId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashdeckRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

