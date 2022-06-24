package com.example.peakproductive.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.peakproductive.models.CardModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FlashCardDao {

    @Insert
    void insert(CardModel model);

    @Delete
    void delete(CardModel model);

    @Update
    void update(CardModel model);

    @Query("SELECT * FROM  card_table")
    LiveData<List<CardModel>> getAllCards();
}
