package com.example.seladanghijau.projectdrlatif;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.session.PlaybackState;
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
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList, listBuku;
    String[] menus, buku;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register android objects
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        listBuku = (ListView) findViewById(R.id.listBuku);

        menus = getResources().getStringArray(R.array.menus); // get list of menus from xml file
        buku = getResources().getStringArray(R.array.buku);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer

        // list utk buku
        listBuku.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, buku));
        listBuku.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) { // listen for item(menu) click in the menu drawer
        switch (parent.getId()) { // get id utk AdapterView(ListView, GridView atau Spinner) yang kita click
            case R.id.listBuku :
                startActivity(new Intent(this, BookDetail.class)); // move to page BookDetail(create new activity based on BookDetail)
                break;
            case R.id.menuList :
                menuList.setItemChecked(position, true); // check the item that is being click to true
                getSupportActionBar().setTitle(menus[position]); // set the title of the action bar(top bar) to the menu that has been clicked

                drawerLayout.closeDrawers(); // close the drawer when an item has been clicked
                break;
            default :
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }

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
}
