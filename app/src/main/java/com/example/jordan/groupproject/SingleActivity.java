package com.example.jordan.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        String name, bio, email, affiliation;

        long id = getIntent().getLongExtra("restaurant_id", 0);

        DBHandler dbHelper = new DBHandler(this);
        Cursor c = dbHelper.getRestaurant((int) id);

        if(c.moveToFirst()) {
            name = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NAME)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single, menu);
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

    public void openMap(View v) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        String address = "426 Arlington Avenue, York, ON, M6C 3A2";
        Uri u = Uri.parse("http://maps.google.com/maps?saddr=0,0&daddr=" + address);
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        intent.setData(u);
        startActivity(intent);
    }
}