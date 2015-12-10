package com.example.jordan.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("in Main Activity");

        findViewById(R.id.btnSearchSubmit).setOnClickListener(this);
        findViewById(R.id.btnView).setOnClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnAbout).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        switch (v.getId()){

            case R.id.btnAbout:
                Intent gen = new Intent(this, AboutActivity.class);
                startActivity(gen);
                break;
            case R.id.btnAdd:
                Intent att = new Intent(this, AddActivity.class);
                startActivity(att);
                break;
            case R.id.btnSearchSubmit:
                Intent lea = new Intent(this, SearchActivity.class);
                startActivity(lea);
                break;
            case R.id.btnView:
                Intent map = new Intent(this, ViewActivity.class);
                startActivity(map);
                break;

        }
    }
}
