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
import model.FlashCard;

/**
 *
 * @author ADMIN
 */
public class FlashCardDAO extends DBContext {

    // Get all flash cards by deck_id
    public List<FlashCard> getFlashCardsByDeckId(int deckId) {
        List<FlashCard> cards = new ArrayList<>();
        String query = "SELECT * FROM flashcards WHERE deck_id = ? ORDER BY card_id DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, deckId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlashCard card = new FlashCard(
                        rs.getInt("card_id"),
                        rs.getString("term"),
                        rs.getString("definition"),
                        rs.getInt("deck_id")
                );
                cards.add(card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return cards;
    }

    // Get flash card by card_id
    public FlashCard getFlashCardById(int cardId) {
        String query = "SELECT * FROM flashcards WHERE card_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new FlashCard(
                        rs.getInt("card_id"),
                        rs.getString("term"),
                        rs.getString("definition"),
                        rs.getInt("deck_id")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    // Create new flash card
    public boolean createFlashCard(String term, String definition, int deckId) {
        String query = "INSERT INTO flashcards (term, definition, deck_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, term);
            ps.setString(2, definition);
            ps.setInt(3, deckId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Update flash card
    public boolean updateFlashCard(int cardId, String term, String definition) {
        String query = "UPDATE flashcards SET term = ?, definition = ? WHERE card_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, term);
            ps.setString(2, definition);
            ps.setInt(3, cardId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Delete flash card
    public boolean deleteFlashCard(int cardId) {
        String query = "DELETE FROM flashcards WHERE card_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cardId);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

