/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.FlashdeckRepository;
import Model.Flashdecks;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FlashdeckService {
    private FlashdeckRepository flashdeckRepo = new FlashdeckRepository();
    
    // Get all flashdecks by user ID
    public List<Flashdecks> getFlashdecksByUserId(int userId) {
        return flashdeckRepo.getFlashdecksByUserId(userId);
    }
    
    // Get flashdeck by deck ID
    public Flashdecks getFlashdeckById(int deckId) {
        return flashdeckRepo.getFlashdeckById(deckId);
    }
    
    // Create new flashdeck
    public boolean createFlashdeck(int userId, String deckName) {
        if (deckName == null || deckName.trim().isEmpty()) {
            return false;
        }
        return flashdeckRepo.createFlashdeck(userId, deckName.trim());
    }
    
    // Update flashdeck
    public boolean updateFlashdeck(int deckId, String deckName) {
        if (deckName == null || deckName.trim().isEmpty()) {
            return false;
        }
        return flashdeckRepo.updateFlashdeck(deckId, deckName.trim());
    }
    
    // Delete flashdeck
    public boolean deleteFlashdeck(int deckId) {
        return flashdeckRepo.deleteFlashdeck(deckId);
    }
}

