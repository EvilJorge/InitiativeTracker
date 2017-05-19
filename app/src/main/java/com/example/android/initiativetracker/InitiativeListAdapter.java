package com.example.android.initiativetracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cpalomares on 5/4/2017.
 */

public class InitiativeListAdapter extends RecyclerView.Adapter<InitiativeListAdapter.ViewHolder> {
    // Store a member variable for the contacts
    private ArrayList<PCClass> mInitiativeList;

    // Store the context for easy access
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView mNameTextView;
        public ViewHolder(View itemView){
            super(itemView);

            mNameTextView = (TextView) itemView.findViewById(R.id.name);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public InitiativeListAdapter(Context context, ArrayList<PCClass> initList){
        mInitiativeList = initList;
        mContext = context;
    }

    // Easy access to the context object in the RecyclerView
    private Context getContext(){
        return mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public InitiativeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();

        // Inflate the custom view
        View itemView = (View) LayoutInflater.from(context)
                .inflate(R.layout.item_initiative_list, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(InitiativeListAdapter.ViewHolder viewHolder, int position){
        // Get the data model based on position
        PCClass newCharacter = mInitiativeList.get(position);

        // Set items based on your views and data model
        TextView textView = viewHolder.mNameTextView;
        textView.setText(newCharacter.name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return mInitiativeList.size();
    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        // Get the data item for this position
//        PCClass actor = getItem(position);
//
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_initiative_list, parent, false);
//        }
//
//        // Lookup view for data population
//        TextView actorName = (TextView) convertView.findViewById(R.id.name);
//
//        // Populate the data into the template view using the data object
//        actorName.setText(actor.name);
//
//        // Return the completed view to render on screen
//        return convertView;
//    }
}
