/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Flashcards;
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
public class FlashcardRepository extends DBContext {
    
    // Get all flashcards by deck ID
    public List<Flashcards> getFlashcardsByDeckId(int deckId) {
        List<Flashcards> lst = new ArrayList<>();
        String query = "SELECT * FROM flashcards WHERE deck_id = ? ORDER BY card_id DESC";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, deckId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Flashcards card = new Flashcards(
                    rs.getInt("card_id"),
                    rs.getInt("deck_id"),
                    rs.getString("term"),
                    rs.getString("definition")
                );
                lst.add(card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return lst;
    }
    
    // Get flashcard by card ID
    public Flashcards getFlashcardById(int cardId) {
        String query = "SELECT * FROM flashcards WHERE card_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, cardId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new Flashcards(
                    rs.getInt("card_id"),
                    rs.getInt("deck_id"),
                    rs.getString("term"),
                    rs.getString("definition")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }
    
    // Create new flashcard
    public boolean createFlashcard(int deckId, String term, String definition) {
        String query = "INSERT INTO flashcards (deck_id, term, definition) VALUES (?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, deckId);
            p.setString(2, term);
            p.setString(3, definition);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Update flashcard
    public boolean updateFlashcard(int cardId, String term, String definition) {
        String query = "UPDATE flashcards SET term = ?, definition = ? WHERE card_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setString(1, term);
            p.setString(2, definition);
            p.setInt(3, cardId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
    
    // Delete flashcard
    public boolean deleteFlashcard(int cardId) {
        String query = "DELETE FROM flashcards WHERE card_id = ?";
        try {
            PreparedStatement p = connection.prepareStatement(query);
            p.setInt(1, cardId);
            int affected = p.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }
}

