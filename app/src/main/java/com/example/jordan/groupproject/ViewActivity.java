package com.example.jordan.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ArrayList<DBItems> items = new ArrayList<DBItems>();
    int index = 0;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String name, email;

        System.out.println("View Activity OK");

        DBHandler dbHelper = new DBHandler(this);
        Cursor c = dbHelper.getAllRestaurants();

        if(c.moveToFirst()) {

            while(!c.isAfterLast()) {
                name = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NAME)));

                Restaurant item = new Restaurant();
                item.setName(name);
                item.setId(c.getInt(c.getColumnIndexOrThrow((RestaurantContract.Restaurants._ID))));
                items.add(item);
                c.moveToNext();
            }
        }
        final ListView lvItems = (ListView) findViewById(R.id.lvAll);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                long itemValue = ((Restaurant) lvItems.getItemAtPosition(position)).getId();
                Intent intent = new Intent(ViewActivity.this, SingleActivity.class);
                intent.putExtra("restaurant_id", itemValue);
                startActivity(intent);
            }
        });

        lvItems.setAdapter(new ItemsAdapter(this, items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
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
}

