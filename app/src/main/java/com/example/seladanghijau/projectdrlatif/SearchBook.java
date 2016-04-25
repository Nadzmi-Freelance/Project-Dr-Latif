package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
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
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class SearchBook extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ProgressDialog pDialog;
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    EditText txtSearchByTitle;
    ListView lvListBook,menuList;
    Button btnSearchByTitle, btnSearchAll;

    String[] menus;
    List<String> listTajukBuku;
    String inBookTitle;
    int[] bookId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        // ------------------------ register activity objects ----------------------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        txtSearchByTitle = (EditText) findViewById(R.id.txtSearchByTitle);
        btnSearchByTitle = (Button) findViewById(R.id.btnSearchByTitle);
        btnSearchAll = (Button) findViewById(R.id.btnSearchAll);
        lvListBook = (ListView) findViewById(R.id.lvBookList);
        menuList = (ListView) findViewById(R.id.menuList);
        // -------------------------------------------------------------------------------------

        // --------------------------- register OnClickListener -------------------------------
        btnSearchByTitle.setOnClickListener(this);
        btnSearchAll.setOnClickListener(this);
        // ------------------------------------------------------------------------------------

        menus = getResources().getStringArray(R.array.menuMain); // get list of menus from xml file
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer
    }

    // -------------------------------------------- OnClick Listener ----------------------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearchAll:
                Intent mainActivity = new Intent(this, MainActivity.class);

                startActivity(mainActivity);
                break;
            case R.id.btnSearchByTitle:
                inBookTitle = txtSearchByTitle.getText().toString();

                new GetBook().execute();
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
                        Intent logoutPage = new Intent(SearchBook.this, LogoutPage.class);
                        startActivity(logoutPage);
                        break;
                }

                drawerLayout.closeDrawers(); // close the drawer when an item has been clicked
                break;
            case R.id.lvBookList:
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

    private class GetBook extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(SearchBook.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            inBookTitle = txtSearchByTitle.getText().toString();

            try {
                HTTPHandler httpHandler = new HTTPHandler();

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                postData.add(new BasicNameValuePair("inBookTitle", "" + inBookTitle));
                // -------------------------------------------------------------------------------------------------------------

                String responseData = httpHandler.result("http://seladanghijau.netai.net/php/SearchBook.php");

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) { // http request "OK": successfully connect to database
                    JSONObject jObj = new JSONObject(responseData);
                    JSONArray jArray = jObj.getJSONArray("books");

                    bookId = new int[jArray.length()]; // set the size according to the size of the json array
                    for(int y=0 ; y<jArray.length() ; y++) { // retrieve suma data dari json
                        JSONObject tempJSON = jArray.getJSONObject(y);

                        // get data from JSON(elementarily)
                        String book_title = tempJSON.getString("book_title");
                        bookId[y] = tempJSON.getInt("book_id");

                        listTajukBuku.add(book_title); // retrieve tajuk buku masuk kedalam list buku
                    }
                } return false;
            } catch (Exception e) { e.printStackTrace(); }

            return true; // return true because successfully carry on the operation
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                // list utk buku
                lvListBook.setAdapter(new ArrayAdapter<>(SearchBook.this, R.layout.menulist_layout, listTajukBuku)); // display list buku dalam arraylist ke ListView
                lvListBook.setOnItemClickListener(SearchBook.this); // set onclicklistener utk setiap item dlm ListView
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
