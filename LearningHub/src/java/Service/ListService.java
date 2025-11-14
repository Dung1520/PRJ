/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.ListRepository;
import Model.Lists;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ListService {
    private final ListRepository listRepo = new ListRepository();
    
    // Get all lists by user ID
    public List<Lists> getListsByUserId(int userId) {
        return listRepo.getListsByUserId(userId);
    }
    
    // Get list by list ID
    public Lists getListById(int listId) {
        return listRepo.getListById(listId);
    }
    
    // Create new list
    public boolean createList(int userId, String listName) {
        if (listName == null || listName.trim().isEmpty()) {
            return false;
        }
        return listRepo.createList(userId, listName.trim());
    }
    
    // Update list
    public boolean updateList(int listId, String listName) {
        if (listName == null || listName.trim().isEmpty()) {
            return false;
        }
        return listRepo.updateList(listId, listName.trim());
    }
    
    // Delete list
    public boolean deleteList(int listId) {
        return listRepo.deleteList(listId);
    }
}
