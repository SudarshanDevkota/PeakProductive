package com.example.peakproductive.models;


import java.io.Serializable;

public class CardModel implements Serializable {
    private int cardId;
    private String cardTitle;
    private String cardContent;

    private String cardTag;


    public CardModel(int cardId, String cardTitle, String cardContent, String tag) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.cardTag = tag;
    }

    public String getCardTag() {
        return cardTag;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardContent() {
        return cardContent;
    }

    public int getCardId() {
        return cardId;
    }

}
