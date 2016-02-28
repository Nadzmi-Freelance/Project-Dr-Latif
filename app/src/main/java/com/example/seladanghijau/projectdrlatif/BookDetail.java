package com.example.seladanghijau.projectdrlatif;

import android.app.TabActivity;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class BookDetail extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    TabHost tabHost;
    String[] menus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // -------------- register android objects ----------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        // --------------------------------------------------------------

        // -------------- drawer actions --------------------
        menus = getResources().getStringArray(R.array.menus);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(drawerListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus));
        menuList.setOnItemClickListener(this);
        // -------------------------------------------------

        // ------------------- tab actions --------------------
        tabHost.setup(); // setup the tabhost

        // declare TabSpec
        TabHost.TabSpec positiveTabSpec = tabHost.newTabSpec("Tab One");
        TabHost.TabSpec negativeTabSpec = tabHost.newTabSpec("Tab Two");
        TabHost.TabSpec neutralTabSpec = tabHost.newTabSpec("Tab Three");

        // set content for TabSpec
        positiveTabSpec.setContent(R.id.positiveTab);
        negativeTabSpec.setContent(R.id.negativeTab);
        neutralTabSpec.setContent(R.id.neutralTab);

        // set indicator for each tab
        positiveTabSpec.setIndicator("Positive");
        negativeTabSpec.setIndicator("Negative");
        neutralTabSpec.setIndicator("Neutral");

        // add TabSpec to TabHost
        tabHost.addTab(positiveTabSpec);
        tabHost.addTab(negativeTabSpec);
        tabHost.addTab(neutralTabSpec);
        // ----------------------------------------------------
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.menuList :
                menuList.setItemChecked(position, true);
                getSupportActionBar().setTitle(menus[position]);

                drawerLayout.closeDrawers();
                break;
            default :
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerListener.syncState();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerListener.onConfigurationChanged(newConfig);
    }
}
