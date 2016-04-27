package com.example.seladanghijau.projectdrlatif;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class SearchBook extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    // activity elements
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    Button btnSearchByTitle, btnSearchByAuthor, btnSearchAll;

    // other variables
    String[] menus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        // ------------------------ register activity objects ----------------------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        btnSearchByTitle = (Button) findViewById(R.id.btnSearchByTitle);
        btnSearchByAuthor = (Button) findViewById(R.id.btnSearchByAuthor);
        btnSearchAll = (Button) findViewById(R.id.btnSearchAll);
        menuList = (ListView) findViewById(R.id.menuList);
        // -------------------------------------------------------------------------------------

        // --------------------------- register OnClickListener -------------------------------
        btnSearchByTitle.setOnClickListener(this);
        btnSearchByAuthor.setOnClickListener(this);
        btnSearchAll.setOnClickListener(this);
        // ------------------------------------------------------------------------------------

        // -------------------------------------- menu dalam drawer ---------------------------------------------
        menus = getResources().getStringArray(R.array.menuMain); // get list of menus from xml file
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer
        // -----------------------------------------------------------------------------------------------------
    }

    // -------------------------------------------- OnClick Listener ----------------------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearchAll:
                Intent mainActivity = new Intent(this, MainActivity.class);

                startActivity(mainActivity);
                break;
            case R.id.btnSearchByTitle:
                Intent searchBookTitle = new Intent(this, SearchBookTitle.class);

                startActivity(searchBookTitle);
                break;
            case R.id.btnSearchByAuthor:
                Intent searchBookAuthor = new Intent(this, SearchBookAuthor.class);

                startActivity(searchBookAuthor);
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
            case R.id.menuList:
                menuList.setItemChecked(position, true); // check the item that is being click to true
                getSupportActionBar().setTitle(menus[position]); // set the title of the action bar(top bar) to the menu that has been clicked

                switch(position) {
                    case 2:
                        Intent logoutPage = new Intent(SearchBook.this, UserProfile.class);
                        startActivity(logoutPage);
                        break;
                }

                drawerLayout.closeDrawers(); // close the drawer when an item has been clicked
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: // destroy this activity if user clicked back button
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
    // ---------------------------------------------------------------------------------------------------------------------

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
