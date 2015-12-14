package com.example.jordan.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Restaurant> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById(R.id.btnSearchSubmit).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String name, address, number, description, tags;
        final String input = findViewById(R.id.editSearch).toString();
        int x = 0;

        DBHandler dbHelper = new DBHandler(this);

        Cursor c = dbHelper.getAllRestaurants();
        //////////////
        // we need something like
        // Cursor d = dbHelper.getRestaurant(x++);
        // and loop through looking for 'input'
        /////////////

        if(c.moveToFirst()) {

            while (!c.isAfterLast()) {
                name = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NAME)));
                address = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS)));
                number = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NUMBER)));
                description = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION)));
                tags = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_TAGS)));

                Restaurant rest = new Restaurant();
                rest.setName(name);
                rest.setAddress(address);
                rest.setNumber(number);
                rest.setDescription(description);
                rest.setTags(tags);
                rest.setId(c.getInt(c.getColumnIndexOrThrow((RestaurantContract.Restaurants._ID))));
                restaurantList.add(rest);
                
                c.moveToNext();
            }

            final ListView lvItems = (ListView) findViewById(R.id.lvResults);
            lvItems.setAdapter(new RestaurantAdapter(this, restaurantList));
        }
    }
}
