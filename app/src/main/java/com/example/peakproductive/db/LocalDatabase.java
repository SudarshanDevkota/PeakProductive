package com.example.peakproductive.db;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.peakproductive.dao.FlashCardDao;
import com.example.peakproductive.dao.TaskDao;
import com.example.peakproductive.models.CardModel;
import com.example.peakproductive.models.TaskModel;

@Database(entities = {TaskModel.class, CardModel.class},version = 1,exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    public static LocalDatabase database;
    abstract  public  TaskDao getTaskDao();
    abstract public FlashCardDao getFlashCardDao();

    public static LocalDatabase getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),LocalDatabase.class,"database").allowMainThreadQueries().build();
        }
        return database;
    }


}
