package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList, listBuku;
    String[] menus;

    ArrayList<String> listTajukBuku;

    // book's details data
    int[] book_id;

    static ProgressDialog pDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register android objects
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        listBuku = (ListView) findViewById(R.id.listBuku);

        menus = getResources().getStringArray(R.array.menuMain); // get list of menus from xml file
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer

        // load list buku
        listTajukBuku = new ArrayList<>();
        new LoadBookList().execute();
    }

    // --------------------------------------------------- OnClick events ---------------------------------------------------------------
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) { // listen for item(menu) click in the menu drawer
        switch (parent.getId()) { // get id utk AdapterView(ListView, GridView atau Spinner) yang kita click
            case R.id.listBuku :
                // ---------------------------------- go to BookDetail Activity --------------------------------------------
                Intent i = new Intent(this, BookDetail.class);

                // put data to be brought to the second activity
                i.putExtra("book_id", book_id[position]);

                startActivity(i); // move to page BookDetail(create new activity based on BookDetail)
                break;
                // ---------------------------------------------------------------------------------------------------------
            case R.id.menuList :
                menuList.setItemChecked(position, true); // check the item that is being click to true
                getSupportActionBar().setTitle(menus[position]); // set the title of the action bar(top bar) to the menu that has been clicked

                switch(position) {
                    case 2:
                        Intent logoutPage = new Intent(MainActivity.this, LogoutPage.class);
                        startActivity(logoutPage);
                        break;
                }

                drawerLayout.closeDrawers(); // close the drawer when an item has been clicked
                break;
            default :
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }
    // -------------------------------------------------------------------------------------------------------------------------------------

    private class LoadBookList extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Tunggu sebentar...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            try {
                HTTPHandler httpHandler = new HTTPHandler();
                String responseData = httpHandler.result("http://seladanghijau.netai.net/php/retrieve.php");

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) { // http request "OK": successfully connect to database
                    JSONObject jObj = new JSONObject(responseData);
                    JSONArray jArray = jObj.getJSONArray("books");

                    book_id = new int[jArray.length()]; // set the size according to the size of the json array
                    for(int y=0 ; y<jArray.length() ; y++) { // retrieve suma data dari json
                        JSONObject tempJSON = jArray.getJSONObject(y);

                        // get data from JSON(elementarily)
                        String book_title = tempJSON.getString("book_title");
                        book_id[y] = tempJSON.getInt("book_id");

                        listTajukBuku.add(book_title); // retrieve tajuk buku masuk kedalam list buku
                    }

                    return true; // return true because successfully carry on the operation
                }
            } catch (Exception e) { e.printStackTrace(); }

            return false; // return false because unsuccessfully carry on the operation
        }

        protected void onPostExecute(Boolean cond) {
            super.onPostExecute(cond);

            // check if the retrieve operation is successful or not
            if(cond) {
                // list utk buku
                listBuku.setAdapter(new ArrayAdapter<>(MainActivity.this, R.layout.menulist_layout, listTajukBuku)); // display list buku dalam arraylist ke ListView
                listBuku.setOnItemClickListener(MainActivity.this); // set onclicklistener utk setiap item dlm ListView
            } else {
                pDialog.setMessage("Database error");
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }

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
