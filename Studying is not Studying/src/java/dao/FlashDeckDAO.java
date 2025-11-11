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
import model.FlashDeck;

/**
 *
 * @author ADMIN
 */
public class FlashDeckDAO extends DBContext {

    // Get all flash decks by user_id
    public List<FlashDeck> getFlashDecksByUserId(int userId) {
        List<FlashDeck> decks = new ArrayList<>();
        String query = "SELECT * FROM flashdecks WHERE user_id = ? ORDER BY deck_id DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlashDeck deck = new FlashDeck(
                        rs.getInt("deck_id"),
                        rs.getString("deck_name"),
                        rs.getInt("user_id")
                );
                decks.add(deck);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashDeckDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return decks;
    }

    // Get flash deck by deck_id
    public FlashDeck getFlashDeckById(int deckId) {
        String query = "SELECT * FROM flashdecks WHERE deck_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, deckId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new FlashDeck(
                        rs.getInt("deck_id"),
                        rs.getString("deck_name"),
                        rs.getInt("user_id")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashDeckDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    // Create new flash deck
    public boolean createFlashDeck(String deckName, int userId) {
        String query = "INSERT INTO flashdecks (deck_name, user_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, deckName);
            ps.setInt(2, userId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashDeckDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Update flash deck
    public boolean updateFlashDeck(int deckId, String deckName) {
        String query = "UPDATE flashdecks SET deck_name = ? WHERE deck_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, deckName);
            ps.setInt(2, deckId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashDeckDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Delete flash deck
    public boolean deleteFlashDeck(int deckId) {
        String query = "DELETE FROM flashdecks WHERE deck_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, deckId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashDeckDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

