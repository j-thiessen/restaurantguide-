package com.example.jordan.groupproject;

import android.provider.BaseColumns;

public class RestaurantContract {

    private RestaurantContract() {}


    public static abstract class Restaurants implements BaseColumns {
        // first set up the table and columns
        public static final String TABLE_NAME = "Restaurants";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TAGS = "tags";


        public static final String CREATE = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + _ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME_NAME + " TEXT, "
                + COLUMN_NAME_ADDRESS + " TEXT, "
                + COLUMN_NAME_NUMBER + " TEXT, "
                + COLUMN_NAME_DESCRIPTION + " TEXT, "
                + COLUMN_NAME_TAGS + " TEXT"
                + ")";

        public static final String DROP = "DROP TABLE " + TABLE_NAME;

        public static final String INSERT = "";

    }
}
