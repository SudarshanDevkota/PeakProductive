package com.example.peakproductive.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.peakproductive.database.CardDatabaseHelper;

import java.util.ArrayList;

public class CardFactory {
    private ArrayList<CardModel> list;
    private CardDatabaseHelper database;
    private SQLiteDatabase db;

    public CardFactory(Context context) {
        list = new ArrayList<>();
        database = new CardDatabaseHelper(context);


    }


    public CardModel getCard(int id) {
        CardModel model = null;
        for (CardModel c : list) {
            if (c.getCardId() == id) {
                model = c;
            }
        }
        return model;
    }


    public boolean addCard(String title, String content, String tag) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = getContentValues(title, content, tag);
        long ins = db.insert(CardDatabaseHelper.CARD_TABLE, null, cv);
        db.close();
        return ins != -1;
    }

    public ArrayList<CardModel> getCardList() {
        ArrayList<CardModel> list = new ArrayList<>();
        Cursor cursor = null;
        db = database.getReadableDatabase();
        db.beginTransaction();
        try {
            cursor = db.query(CardDatabaseHelper.CARD_TABLE, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_CARD_ID));
                    String title = cursor.getString(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_CARD_TITLE));
                    String content = cursor.getString(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_CARD_CONTENT));
                    String tag = cursor.getString(cursor.getColumnIndex(CardDatabaseHelper.COLUMN_CARD_TAG));

                    CardModel model = new CardModel(id, title, content, tag);
                    list.add(model);


                } while (cursor.moveToNext());
            }


        } finally {
            db.endTransaction();
            cursor.close();

        }
        return list;

    }

    public void updateCard(int id,String title, String content, String tag) {
        ContentValues cv = getContentValues(title,content,tag);
        db = database.getWritableDatabase();
        db.update(CardDatabaseHelper.CARD_TABLE,cv,CardDatabaseHelper.COLUMN_CARD_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteCard(int id) {
        db.delete(CardDatabaseHelper.CARD_TABLE, CardDatabaseHelper.COLUMN_CARD_ID + " =?", new String[]{String.valueOf(id)});
    }

    private ContentValues getContentValues(String title, String content, String tag) {
        ContentValues cv = new ContentValues();
        cv.put(CardDatabaseHelper.COLUMN_CARD_TITLE, title);
        cv.put(CardDatabaseHelper.COLUMN_CARD_CONTENT, content);
        cv.put(CardDatabaseHelper.COLUMN_CARD_TAG, tag);
        return cv;
    }

}
