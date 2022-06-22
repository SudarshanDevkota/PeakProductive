package com.example.peakproductive.db;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.peakproductive.dao.TaskDao;
import com.example.peakproductive.models.TaskModel;

@Database(entities = {TaskModel.class},version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    public static LocalDatabase database;
    abstract  public  TaskDao getTaskDao();

    public static LocalDatabase getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),LocalDatabase.class,"database").allowMainThreadQueries().build();
        }
        return database;
    }


}
