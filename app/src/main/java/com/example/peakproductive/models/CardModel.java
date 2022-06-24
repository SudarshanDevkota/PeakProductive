package com.example.peakproductive.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="card_table")
public class CardModel implements Serializable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "tag")
    private String cardTag;


    public CardModel(){}

    public String getCardTag() {
        return cardTag;
    }

    public String getCardTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCardTag(String cardTag) {
        this.cardTag = cardTag;
    }

    public String getCardContent() {
        return content;
    }

    public int getCardId() {
        return id;
    }

}
