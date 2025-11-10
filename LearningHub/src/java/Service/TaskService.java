/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.TaskRepository;
import Model.Tasks;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaskService {
    private final TaskRepository taskRepo = new TaskRepository();
    
    // Get all tasks by list ID
    public List<Tasks> getTasksByListId(int listId) {
        return taskRepo.getTasksByListId(listId);
    }
    
    // Get task by task ID
    public Tasks getTaskById(int taskId) {
        return taskRepo.getTaskById(taskId);
    }
    
    // Create new task
    public boolean createTask(int listId, String taskName, String description, Date dueDate) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        return taskRepo.createTask(listId, taskName.trim(), 
                description != null ? description.trim() : null, dueDate);
    }
    
    // Update task
    public boolean updateTask(int taskId, String taskName, String description, Date dueDate, boolean isCompleted) {
        if (taskName == null || taskName.trim().isEmpty()) {
            return false;
        }
        return taskRepo.updateTask(taskId, taskName.trim(), 
                description != null ? description.trim() : null, dueDate, isCompleted);
    }
    
    // Toggle task completion status
    public boolean toggleTaskCompletion(int taskId, boolean isCompleted) {
        return taskRepo.toggleTaskCompletion(taskId, isCompleted);
    }
    
    // Delete task
    public boolean deleteTask(int taskId) {
        return taskRepo.deleteTask(taskId);
    }
}
