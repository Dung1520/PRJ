/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class FlashCard {
    private int cardId;
    private String term;
    private String definition;
    private int deckId;

    public FlashCard() {
    }

    public FlashCard(int cardId, String term, String definition, int deckId) {
        this.cardId = cardId;
        this.term = term;
        this.definition = definition;
        this.deckId = deckId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    @Override
    public String toString() {
        return "FlashCard{" + "cardId=" + cardId + ", term=" + term + ", definition=" + definition + ", deckId=" + deckId + '}';
    }
}

