/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.TodoListDAO;
import java.util.List;
import model.TodoList;

/**
 *
 * @author ADMIN
 */
public class TodoListService {
    private TodoListDAO todoListDAO = new TodoListDAO();

    public List<TodoList> getTodoListsByUserId(int userId) {
        return todoListDAO.getTodoListsByUserId(userId);
    }

    public TodoList getTodoListById(int listId) {
        return todoListDAO.getTodoListById(listId);
    }

    public boolean createTodoList(int userId, String listName) {
        if (listName == null || listName.trim().isEmpty()) {
            return false;
        }
        return todoListDAO.createTodoList(userId, listName.trim());
    }

    public boolean updateTodoList(int listId, String listName) {
        if (listName == null || listName.trim().isEmpty()) {
            return false;
        }
        return todoListDAO.updateTodoList(listId, listName.trim());
    }

    public boolean deleteTodoList(int listId) {
        return todoListDAO.deleteTodoList(listId);
    }
}

