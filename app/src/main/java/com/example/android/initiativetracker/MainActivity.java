package com.example.android.initiativetracker;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public PopupWindow mAddPCWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    // Event Handlers
    public void openAddPCWindow(View v){
        showAddPCPopup(v);
    }

    public void addNewPC(View v){
        String name;
        int initiative;
        int ac;

        // Get values from EditText views in PopupWindow
        name = new String(((EditText) mAddPCWindow.getContentView().findViewById(R.id.add_name_entry)).getText().toString());
        initiative = Integer.parseInt(
                ((EditText) mAddPCWindow.getContentView().findViewById(R.id.add_init_entry)).getText().toString()
        );
        ac = Integer.parseInt(
                ((EditText) mAddPCWindow.getContentView().findViewById(R.id.add_ac_entry)).getText().toString()
        );

        InitiativeTrackerFragment initiativeTracker = (InitiativeTrackerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_initiative_tracker);

        // Add new PC to list and dismiss window.
        initiativeTracker.addtoPCList(name, initiative, ac);
        mAddPCWindow.dismiss();
    }

    // Other Methods
    private void showAddPCPopup(View v){
        Toast.makeText(getBaseContext(), "Add PC Window button pressed.", Toast.LENGTH_SHORT).show();
        // Inflate the popup window l
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.popup_addpc, null);

        // Initialize a new instance of popup window
        mAddPCWindow = new PopupWindow(popUpView,
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        // Set an elevation value for popup window if API is 21 or greater.
        if(Build.VERSION.SDK_INT >= 21){
            mAddPCWindow.setElevation(5.0f);
        }

        mAddPCWindow.setFocusable(true);
        mAddPCWindow.update();
        mAddPCWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }
}
