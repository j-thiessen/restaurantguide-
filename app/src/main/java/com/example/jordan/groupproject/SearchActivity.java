package com.example.jordan.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

        switch (v.getId()) {
            case R.id.btnSearchSubmit:

                ArrayList<Restaurant> restaurantList = new ArrayList<>();
                String name, address, number, description, tags;
                EditText searchTerm = (EditText) findViewById(R.id.editSearch);
                DBHandler dbHelper = new DBHandler(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM restaurants WHERE name LIKE \'%" + searchTerm.getText() + "%\'", null);

                if (c.moveToFirst()) {

                    while (!c.isAfterLast()) {
                        name = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NAME)));

                        address = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS)));

                        number = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NUMBER)));

<<<<<<< HEAD
        DBHandler dbHelper = new DBHandler(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor searchCursor = db.rawQuery("SELECT * FROM restaurants WHERE name LIKE \'%" + searchTerm.getText() + "%\'", null);
        ListView items = (ListView)findViewById(R.id.lvResults);
        // the problem here is that the searchCursor needs to somehow be converted to an ArrayList
     //   RestaurantAdapter restaurantCursorAdapter = new RestaurantAdapter(this, searchCursor);
      //  items.setAdapter(restaurantCursorAdapter);
     //   items.setOnItemClickListener(this);
=======
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
                }

                final ListView items = (ListView) findViewById(R.id.lvResults);

                items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int itemPosition = position;
                        long itemValue = ((Restaurant)items.getItemAtPosition(itemPosition)).getId();
                        Intent intent = new Intent(SearchActivity.this, SingleActivity.class);
                        intent.putExtra("restaurant_id", itemValue);
                        startActivity(intent);
                    }
                });

                // the problem here is that the searchCursor needs to somehow be converted to an ArrayList
                RestaurantAdapter restaurantCursorAdapter = new RestaurantAdapter(this, restaurantList);
                items.setAdapter(restaurantCursorAdapter);
                //items.setOnItemClickListener(this);
                break;
        }
>>>>>>> refs/remotes/origin/jordan3
    }
}
