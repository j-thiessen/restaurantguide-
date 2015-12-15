package com.example.jordan.groupproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

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

        //EditText searchTerm = (EditText)findViewById(R.id.textView);

        //DBHandler dbHelper = new DBHandler(this);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Cursor searchCursor = db.rawQuery("SELECT * FROM restaurants WHERE name LIKE \'%" + searchTerm.getText() + "%\'", null);
        //ListView items = (ListView)findViewById(R.id.lvResults);
        // the problem here is that the searchCursor needs to somehow be converted to an ArrayList
       // RestaurantAdapter restaurantCursorAdapter = new RestaurantAdapter(this, searchCursor);
       // items.setAdapter(restaurantCursorAdapter);
     //   items.setOnItemClickListener(this);
    }
}
