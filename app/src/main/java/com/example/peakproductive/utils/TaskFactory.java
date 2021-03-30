package com.example.peakproductive.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.peakproductive.database.CardDatabaseHelper;


import com.example.peakproductive.models.TaskModel;

import java.util.ArrayList;

public class TaskFactory {
    private ArrayList<TaskModel> list;
    private CardDatabaseHelper database;
    private SQLiteDatabase db;


    public TaskFactory(Context context) {
        list = new ArrayList<>();
        database = new CardDatabaseHelper(context);
    }

    public boolean addTask(String task,String catagory,int iscompleted) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = getContentValues(task,catagory,iscompleted);
        long ins = db.insert(CardDatabaseHelper.TASK_TABLE, null, cv);
        db.close();
        return ins != -1;
    }
    public void updateTask(int id,String task,String catagory,int iscompleted) {
        ContentValues cv = getContentValues(task,catagory,iscompleted);
        db = database.getWritableDatabase();
        db.update(CardDatabaseHelper.TASK_TABLE,cv,CardDatabaseHelper.COLUMN_TASK_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }

    public void deleteTask(int id) {
        db = database.getWritableDatabase();
        db.delete(CardDatabaseHelper.TASK_TABLE, CardDatabaseHelper.COLUMN_TASK_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateTaskState(int id,int currentState){
        ContentValues cv = new ContentValues();
        cv.put(CardDatabaseHelper.COLUMN_TASK_ISCOMPLETE,currentState);
        db= database.getWritableDatabase();
        db.update(CardDatabaseHelper.TASK_TABLE,cv,CardDatabaseHelper.COLUMN_TASK_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<TaskModel> getTaskList() {
        ArrayList<TaskModel> list = new ArrayList<>();
        Cursor cursor = null;
        db = database.getReadableDatabase();
        db.beginTransaction();
        try {
            cursor = db.query(CardDatabaseHelper.TASK_TABLE, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_TASK_ID));
                    String taskDetail = cursor.getString(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_TASK_DESCRIPTION));
                    String taskCatagory = cursor.getString(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_TASK_CATAGORY));
                    boolean done = cursor.getInt(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_TASK_ISCOMPLETE))==1;


                    TaskModel model = new TaskModel(id, taskDetail,taskCatagory,done);
                    list.add(model);


                } while (cursor.moveToNext());
            }


        } finally {
            db.endTransaction();
            cursor.close();

        }
        return list;

    }



    private ContentValues getContentValues(String task,String catagory, int isCompleted) {
        ContentValues cv = new ContentValues();
        cv.put(CardDatabaseHelper.COLUMN_TASK_DESCRIPTION, task);
        cv.put(CardDatabaseHelper.COLUMN_TASK_CATAGORY,catagory);
        cv.put(CardDatabaseHelper.COLUMN_TASK_ISCOMPLETE, isCompleted);
        return cv;
    }
}
