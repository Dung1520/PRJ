/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class TodoList {
    private int listId;
    private int userId;
    private String listName;

    public TodoList() {
    }

    public TodoList(int listId, int userId, String listName) {
        this.listId = listId;
        this.userId = userId;
        this.listName = listName;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Override
    public String toString() {
        return "TodoList{" + "listId=" + listId + ", userId=" + userId + ", listName=" + listName + '}';
    }
}

