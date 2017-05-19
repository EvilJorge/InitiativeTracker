package com.example.android.initiativetracker;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InitiativeTrackerFragment extends Fragment {

    // Data Lists
    private ArrayList<PCClass> mPCList = new ArrayList<PCClass>();
    private ArrayList<MonsterClass> mMonsterList = new ArrayList<MonsterClass>();
    private ArrayList<PCClass> mInitiativeList = new ArrayList<PCClass>();

    // Adapters
    private PCListAdapter mPCListAdapter;
    private MonsterListAdapter mMonsterListAdapter;
    private InitiativeListAdapter mInitiativeListAdapter;

    // ListViews
    private ListView mPCListView;
    private ListView mEnemyListView;
    private RecyclerView mInitiativeRecyclerView;

    // LayoutManager
    private RecyclerView.LayoutManager mInitiativeLayoutManager;

    public InitiativeTrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_initiative_tracker, container, false);
        //ViewGroup.LayoutParams InitiativeListViewParams = mInitiativeListView.getLayoutParams();

        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            populatePCList();
            populateEnemyList();
        } else {
            mPCList = savedInstanceState.getParcelableArrayList("key");
        }

        populateInitiativeList();
        // Attach lists to List Adapters and set the Adapters to the ListViews
        mPCListAdapter = new PCListAdapter(getContext(), mPCList);
        mPCListView = (ListView) rootView.findViewById(R.id.pc_listview);
        mPCListView.setAdapter(mPCListAdapter);

        mMonsterListAdapter = new MonsterListAdapter(getContext(), mMonsterList);
        mEnemyListView = (ListView) rootView.findViewById(R.id.enemy_listview);
        mEnemyListView.setAdapter(mMonsterListAdapter);

        mInitiativeListAdapter = new InitiativeListAdapter(getContext(), mInitiativeList);
        mInitiativeRecyclerView = (RecyclerView) rootView.findViewById(R.id.initiative_recview);
        mInitiativeRecyclerView.setAdapter(mInitiativeListAdapter);


        // Calculate size and set LayoutManager for InitiativeList
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (80 * scale + 0.5f);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mInitiativeRecyclerView.getLayoutParams().height = pixels * mInitiativeList.size();
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        } else {
            mInitiativeRecyclerView.getLayoutParams().width = pixels * mInitiativeList.size();
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }
        mInitiativeRecyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putParcelableArrayList("key", mPCList);
        super.onSaveInstanceState(outState);
    }

    public void populatePCList(){
        String[] nameList = {"Ashwold", "Briana", "Cyran", "Dante", "Rashe"};
        String name;
        int init;
        int ac;

        for(init = 0; init < 5; init++){
            name = nameList[init];
            ac = 10+ nameList[init].length();
            PCClass newPC = new PCClass(name, init, ac);
            mPCList.add(newPC);
        }
    }

    public void populateEnemyList(){
        String[] nameList = {"Drow Elves", "Skeletons", "Zombies"};
        String name;
        int init;
        int ac;

        for(init = 0; init < 3; init++){
            name = nameList[init];
            ac = 10+ nameList[init].length();
            MonsterClass newMonster = new MonsterClass(name, init, ac);
            mMonsterList.add(newMonster);
        }
    }

    public void populateInitiativeList(){
        for (PCClass nextPC:mPCList) {
            mInitiativeList.add(nextPC);
        }
    }

    // Public Methods
    public void addtoPCList(String name, int initiative, int ac){
        PCClass newPC = new PCClass(name, initiative, ac);
        mPCList.add(newPC);
        mPCListAdapter.notifyDataSetChanged();
    }
}
