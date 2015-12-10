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

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public void addRestaurant(DBItems item) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_EMAIL, item.getEmail());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RESTAURANT, null, values);
        db.close();
    }

    public Cursor selectAll(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                RestaurantContract.DBItems._ID,
                RestaurantContract.DBItems.COLUMN_NAME_NAME,
                RestaurantContract.DBItems.COLUMN_NAME_EMAIL
        };

        // all the nulls take care of where, group by, having etc parameters
        return db.query(RestaurantContract.DBItems.TABLE_NAME,
                projection,
                null, // where fields
                null, // where arguments
                null, // group by
                null, // having
                null // order by ... limit also exists
        );

    }

    public DBItems findRestaurant(String itemname) {
        String query = "Select * FROM " + TABLE_RESTAURANT + " WHERE " + COLUMN_NAME + " =  \"" + itemname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DBItems item = new DBItems();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setName(cursor.getString(1));
            item.setEmail(cursor.getString(1));
            cursor.close();
        } else {
            item = null;
        }
        db.close();
        return item;
    }

    public boolean deleteRestaurant(String itemName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_RESTAURANT + " WHERE " + COLUMN_NAME + " =  \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DBItems item = new DBItems();

        if (cursor.moveToFirst()) {
            item.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_RESTAURANT, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(item.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}