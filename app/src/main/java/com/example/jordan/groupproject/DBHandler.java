package com.example.jordan.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rest.db";
    private static final int DATABASE_VERSION = 6;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DBHandler onCreate OK");

        System.out.println("Creating database... " + DATABASE_NAME + ", version " + DATABASE_VERSION);
        db.execSQL(RestaurantContract.DBItems.CREATE);
        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading database... " + DATABASE_NAME + ", from version " + oldVersion + " to version " + newVersion);
        // used for versions higher than 1; you could drop tables then recreate, etc
        // don't do this in production applications..
        db.execSQL(RestaurantContract.DBItems.DROP);
        onCreate(db);
    }

    // methods we added:

    public Cursor getAll() {
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

    // to get one restaurant
    public Cursor getRestaurant(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                RestaurantContract.DBItems._ID,
                RestaurantContract.DBItems.COLUMN_NAME_NAME,
                RestaurantContract.DBItems.COLUMN_NAME_EMAIL
        };

        // all the nulls take care of where, group by, having etc parameters
        return db.query(RestaurantContract.DBItems.TABLE_NAME,
                projection,
                "_ID = ?",                  // where fields
                new String[] {"" + id},     // where arguments
                null,                       // group by
                null,                       // having
                null                        // order by ... limit also exists
        );
    }

    public void initialize(SQLiteDatabase db) {

        ContentValues values = new ContentValues();

        addRestaurant("The MacDonaldson", "mac@donaldson.son", db);
        addRestaurant("Bob Robbins", "bob@robbins.net", db);

        System.out.println("Initialized Successfully");
    }

    public void addRestaurant(String name, String email, SQLiteDatabase db) {

        ContentValues values = new ContentValues();

        values.put(RestaurantContract.DBItems.COLUMN_NAME_NAME,name);
        values.put(RestaurantContract.DBItems.COLUMN_NAME_EMAIL,email);

        long id = db.insert(RestaurantContract.DBItems.TABLE_NAME, null, values);

        System.out.println("Added Successfully");
    }
}