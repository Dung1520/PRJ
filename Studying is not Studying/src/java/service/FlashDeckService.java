/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.FlashDeckDAO;
import java.util.List;
import model.FlashDeck;

/**
 *
 * @author ADMIN
 */
public class FlashDeckService {
    private FlashDeckDAO flashDeckDAO = new FlashDeckDAO();

    public List<FlashDeck> getFlashDecksByUserId(int userId) {
        return flashDeckDAO.getFlashDecksByUserId(userId);
    }

    public FlashDeck getFlashDeckById(int deckId) {
        return flashDeckDAO.getFlashDeckById(deckId);
    }

    public boolean createFlashDeck(String deckName, int userId) {
        if (deckName == null || deckName.trim().isEmpty()) {
            return false;
        }
        return flashDeckDAO.createFlashDeck(deckName.trim(), userId);
    }

    public boolean updateFlashDeck(int deckId, String deckName) {
        if (deckName == null || deckName.trim().isEmpty()) {
            return false;
        }
        return flashDeckDAO.updateFlashDeck(deckId, deckName.trim());
    }

    public boolean deleteFlashDeck(int deckId) {
        return flashDeckDAO.deleteFlashDeck(deckId);
    }
}

