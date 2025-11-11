/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class FlashDeck {
    private int deckId;
    private String deckName;
    private int userId;

    public FlashDeck() {
    }

    public FlashDeck(int deckId, String deckName, int userId) {
        this.deckId = deckId;
        this.deckName = deckName;
        this.userId = userId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FlashDeck{" + "deckId=" + deckId + ", deckName=" + deckName + ", userId=" + userId + '}';
    }
}

