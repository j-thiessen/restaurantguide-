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

public class SingleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        findViewById(R.id.btnOpenMap).setOnClickListener(this);

        String name, address, number, description, tags;

        long id = getIntent().getLongExtra("restaurant_id", 0);

        DBHandler dbHelper = new DBHandler(this);
        Cursor c = dbHelper.getRestaurant((int) id);

        if(c.moveToFirst()) {
            name = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NAME)));
            ((TextView) findViewById(R.id.txtName)).setText(name);

            address = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS)));
            ((TextView) findViewById(R.id.txtAddress)).setText(address);

            number = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NUMBER)));
            ((TextView) findViewById(R.id.txtNumber)).setText(number);

            description = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION)));
            ((TextView) findViewById(R.id.txtDescription)).setText(description);

            tags = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_TAGS)));
            ((TextView) findViewById(R.id.txtTags)).setText(tags);
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

    @Override
    public void onClick(View v) {
        TextView address = (TextView) findViewById(R.id.txtAddress);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri u = Uri.parse("http://maps.google.com/maps?daddr="
                + address.getText().toString()
                );
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        intent.setData(u);
        startActivity(intent);
    }
}