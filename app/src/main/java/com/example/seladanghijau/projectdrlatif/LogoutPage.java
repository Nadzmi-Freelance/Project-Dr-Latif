package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class LogoutPage extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    // element in activity
    ProgressDialog pDialog;
    Button logout;

    // element dalam drawer
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    String[] menus, userType;

    // sharedpreference data
    SharedPreferences userData;
    SharedPreferences.Editor userDataEditor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_page);

        // ---------------------- setup element in activity ---------------------------
        logout = (Button) findViewById(R.id.logout);
        // ----------------------------------------------------------------------------

        // ------------------- setup element in Drawer ----------------------------
        // element dlm drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);

        menus = getResources().getStringArray(R.array.menuLogin); // get list of menus from xml file

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        // ------------------------------------------------------------------------

        // ------------------------- setup sharedpreference ----------------------------------
        userData = getSharedPreferences(LoginPage.USER_PREFERENCES, MODE_PRIVATE);
        userDataEditor = userData.edit();
        // -----------------------------------------------------------------------------------

        // ---------------------- Setup OnClickListener in activity -----------------------------
        logout.setOnClickListener(this);
        // --------------------------------------------------------------------------------------

        // --------------------------- setup OnItemClickListener in drawer ----------------------------
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer
        // --------------------------------------------------------------------------------------------
    }

    // --------------------------- OnClickListener & OnItemClickListener -----------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.logout:
                userDataEditor.clear();
                userDataEditor.putInt(LoginPage.LOGIN_STAT, LoginPage.NOTLOGGEDIN);
                userDataEditor.commit();

                Intent loginPage = new Intent(LogoutPage.this, LoginPage.class);
                startActivity(loginPage);
                finish();
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
        }
    }
    // -----------------------------------------------------------------------------------------------------------

    // ------------------------------------- AsyncTask private class ----------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------- actions for drawer -------------------------------------------------------------
    protected void onPostCreate(Bundle savedInstanceState) { // used for syncing the state of the icon on left, up most of the screen
        super.onPostCreate(savedInstanceState);

        drawerListener.syncState(); // syncing the state of the icon on left, up most of the screen
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { // handle action bar item click(top bar)
        if(drawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig) { // detect when the configuration(landscape or portrait) of the screen change
        super.onConfigurationChanged(newConfig);

        drawerListener.onConfigurationChanged(newConfig); // change to new configuration
    }
    // ---------------------------------------------------------------------------------------------------------------------------------------------------
}
