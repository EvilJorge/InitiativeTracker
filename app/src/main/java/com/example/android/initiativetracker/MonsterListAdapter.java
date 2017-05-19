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

public class MonsterListAdapter extends ArrayAdapter<MonsterClass> {
    public MonsterListAdapter(Context context, ArrayList<MonsterClass> monsterList){
        super(context, 0, monsterList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        MonsterClass monster = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_enemy_list, parent, false);
        }

        // Lookup view for data population
        TextView monsterName = (TextView) convertView.findViewById(R.id.enemy_name);
        TextView initiativeValue = (TextView) convertView.findViewById(R.id.initiative_value);
        TextView acValue = (TextView) convertView.findViewById(R.id.ac_value);

        // Populate the data into the template view using the data object
        monsterName.setText(monster.name);
        initiativeValue.setText("Init " + monster.initiative);
        acValue.setText("AC " + monster.AC);

        // Return the completed view to render on screen
        return convertView;
    }
}
