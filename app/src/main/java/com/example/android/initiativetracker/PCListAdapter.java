package com.example.android.initiativetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cpalomares on 5/3/2017.
 */

public class PCListAdapter extends ArrayAdapter<Actor> {
    public PCListAdapter(Context context, ArrayList<Actor> pcList){
        super(context, 0, pcList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Actor pc = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hero_list, parent, false);
        }

        // Lookup view for data population
        TextView pcName = (TextView) convertView.findViewById(R.id.pc_name);
        TextView initiativeValue = (TextView) convertView.findViewById(R.id.initiative_value);
        TextView acValue = (TextView) convertView.findViewById(R.id.ac_value);

        // Populate the data into the template view using the data object
        pcName.setText(pc.name);
        initiativeValue.setText("Init " + pc.initiative);
        acValue.setText("AC " + pc.AC);

        // Return the completed view to render on screen
        return convertView;
    }
}
