package com.example.jordan.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 11;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating database... " + DATABASE_NAME + ", version " + DATABASE_VERSION);
        db.execSQL(RestaurantContract.Restaurants.CREATE);
        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading database... " + DATABASE_NAME + ", from version " + oldVersion + " to version " + newVersion);
        // used for versions higher than 1; you could drop tables then recreate, etc
        // don't do this in production applications..
        db.execSQL(RestaurantContract.Restaurants.DROP);
        onCreate(db);
    }


    public Cursor getAllRestaurants() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                RestaurantContract.Restaurants._ID,
                RestaurantContract.Restaurants.COLUMN_NAME_NAME,
                RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS,
                RestaurantContract.Restaurants.COLUMN_NAME_NUMBER,
                RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION,
                RestaurantContract.Restaurants.COLUMN_NAME_TAGS
        };

        // all the nulls take care of where, group by, having etc parameters
        return db.query(RestaurantContract.Restaurants.TABLE_NAME,
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
                RestaurantContract.Restaurants._ID,
                RestaurantContract.Restaurants.COLUMN_NAME_NAME,
                RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS,
                RestaurantContract.Restaurants.COLUMN_NAME_NUMBER,
                RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION,
                RestaurantContract.Restaurants.COLUMN_NAME_TAGS
        };

        // all the nulls take care of where, group by, having etc parameters
        return db.query(RestaurantContract.Restaurants.TABLE_NAME,
                projection,
                "_ID = ?",                  // where fields
                new String[] {"" + id},     // where arguments
                null,                       // group by
                null,                       // having
                null                        // order by ... limit also exists
        );
    }

    public void initialize(SQLiteDatabase db) {
        //SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RestaurantContract.Restaurants.COLUMN_NAME_NAME,"The Restaurant");
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS,"123 Unknown Street");
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_NUMBER,"416-123-4567");
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION,"This is the description of this restaurant");
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_TAGS,"Italian,Pasta, Vegeterian");

        long id = db.insert(RestaurantContract.Restaurants.TABLE_NAME, null, values);

        System.out.println("Item " + id + " added.");

    }

    public void addRestaurant(String name, String address, String number, String description, String tags) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(RestaurantContract.Restaurants.COLUMN_NAME_NAME, name);
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS, address);
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_NUMBER, number);
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION, description);
        values.put(RestaurantContract.Restaurants.COLUMN_NAME_TAGS, tags);

        long id = db.insert(RestaurantContract.Restaurants.TABLE_NAME, null, values);

        System.out.println("Yay!");
    }
}
