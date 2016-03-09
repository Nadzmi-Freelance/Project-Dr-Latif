package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BookDetail extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    TabHost tabHost;
    TextView tajukBuku, infoBuku;
    String[] menus;

    // data from other activities
    Bundle bundledData;
    String book_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // -------------- register android objects ----------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tajukBuku = (TextView) findViewById(R.id.tajukBuku);
        infoBuku = (TextView) findViewById(R.id.infoBuku);
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

        // ------------------------ set layout utk detail buku -------------------------
        bundledData = getIntent().getExtras(); // prepare to retrieve bundled data from previous activity
        book_title = bundledData.getString("book_title"); // get bundled data from previous activity

        // algorithm to retrieve book detail from external database

        // set layout
        tajukBuku.setText(book_title);
        // -----------------------------------------------------------------------------
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

    // I overide these function to make destroy this activity after user has exited it with back button,
    // so that it will not burden the machine to handle many activities, thus increase speed of these application
    public void onBackPressed() {
        // super.onBackPressed();
        // i comment the above command cuz i want to overide these method
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: // destroy this activity if user clicked back button
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
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
