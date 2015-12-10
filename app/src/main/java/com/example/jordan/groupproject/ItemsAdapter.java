package com.example.jordan.groupproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jordan on 2015-12-10.
 */
public class ItemsAdapter extends ArrayAdapter<DBItems> {

    public ItemsAdapter(Context context, ArrayList<DBItems> restaurants) {
        super(context, 0, restaurants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DBItems restaurant = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_single, parent, false);
        }
        // Lookup view for data population
       // TextView  = (TextView) convertView.findViewById(R.id.);
        // Populate the data into the template view using the data object
     //   .setText(.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}