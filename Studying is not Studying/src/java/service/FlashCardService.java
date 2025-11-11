/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.FlashCardDAO;
import java.util.List;
import model.FlashCard;

/**
 *
 * @author ADMIN
 */
public class FlashCardService {
    private FlashCardDAO flashCardDAO = new FlashCardDAO();

    public List<FlashCard> getFlashCardsByDeckId(int deckId) {
        return flashCardDAO.getFlashCardsByDeckId(deckId);
    }

    public FlashCard getFlashCardById(int cardId) {
        return flashCardDAO.getFlashCardById(cardId);
    }

    public boolean createFlashCard(String term, String definition, int deckId) {
        if (term == null || term.trim().isEmpty()) {
            return false;
        }
        return flashCardDAO.createFlashCard(term.trim(), definition, deckId);
    }

    public boolean updateFlashCard(int cardId, String term, String definition) {
        if (term == null || term.trim().isEmpty()) {
            return false;
        }
        return flashCardDAO.updateFlashCard(cardId, term.trim(), definition);
    }

    public boolean deleteFlashCard(int cardId) {
        return flashCardDAO.deleteFlashCard(cardId);
    }
}

