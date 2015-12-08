package com.example.jordan.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restaurantdb.db";
    private static final String TABLE_RESTAURANT = "restaurant";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";

    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
    }

    public void addSpeaker(DBItems speaker) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, speaker.getName());
        values.put(COLUMN_EMAIL, speaker.getEmail());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RESTAURANT, null, values);
        db.close();
    }


    public DBItems findSpeaker(String speakername) {
        String query = "Select * FROM " + TABLE_RESTAURANT + " WHERE " + COLUMN_NAME + " =  \"" + speakername + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DBItems speaker = new DBItems();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            speaker.setId(Integer.parseInt(cursor.getString(0)));
            speaker.setName(cursor.getString(1));
            speaker.setEmail(cursor.getString(1));
            cursor.close();
        } else {
            speaker = null;
        }
        db.close();
        return speaker;
    }

    public boolean deleteSpeaker(String speakerName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_RESTAURANT + " WHERE " + COLUMN_NAME + " =  \"" + speakerName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DBItems speaker = new DBItems();

        if (cursor.moveToFirst()) {
            speaker.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_RESTAURANT, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(speaker.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }