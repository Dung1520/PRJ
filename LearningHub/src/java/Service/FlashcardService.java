/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.FlashcardRepository;
import Model.Flashcards;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FlashcardService {
    private FlashcardRepository flashcardRepo = new FlashcardRepository();
    
    // Get all flashcards by deck ID
    public List<Flashcards> getFlashcardsByDeckId(int deckId) {
        return flashcardRepo.getFlashcardsByDeckId(deckId);
    }
    
    // Get flashcard by card ID
    public Flashcards getFlashcardById(int cardId) {
        return flashcardRepo.getFlashcardById(cardId);
    }
    
    // Create new flashcard
    public boolean createFlashcard(int deckId, String term, String definition) {
        if (term == null || term.trim().isEmpty() || definition == null || definition.trim().isEmpty()) {
            return false;
        }
        return flashcardRepo.createFlashcard(deckId, term.trim(), definition.trim());
    }
    
    // Update flashcard
    public boolean updateFlashcard(int cardId, String term, String definition) {
        if (term == null || term.trim().isEmpty() || definition == null || definition.trim().isEmpty()) {
            return false;
        }
        return flashcardRepo.updateFlashcard(cardId, term.trim(), definition.trim());
    }
    
    // Delete flashcard
    public boolean deleteFlashcard(int cardId) {
        return flashcardRepo.deleteFlashcard(cardId);
    }
}

