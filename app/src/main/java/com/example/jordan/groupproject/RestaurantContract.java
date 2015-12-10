package com.example.jordan.groupproject;

import android.provider.BaseColumns;

/**
 * Created by Jordan on 2015-12-08.
 */
public class RestaurantContract {
    private RestaurantContract() {}

    public static abstract class DBItems implements BaseColumns {
        // first set up the table and columns
        public static final String TABLE_NAME = "restaurant";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";

        public static final String CREATE = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + _ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME_NAME + " TEXT, "
                + COLUMN_NAME_EMAIL + " TEXT"
                + ")";

        public static final String DROP = "DROP TABLE " + TABLE_NAME;

        public static final String INSERT = "";

    }
}
