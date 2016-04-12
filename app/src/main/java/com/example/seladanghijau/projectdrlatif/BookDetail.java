package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class BookDetail extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    // elements in activity
    static ProgressDialog pDialog;
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    TabHost tabHost;
    Button viewPDF, commentButton;
    TextView tajukBuku, accessionnoBuku, authorBuku;
    ListView commentNeutral, commentPositive, commentNegative;
    String[] menus;

    TextView debug;

    // data from other activities
    static Book book;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // -------------- register android objects ----------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        viewPDF = (Button) findViewById(R.id.viewPDF);
        commentButton = (Button) findViewById(R.id.comment);
        tajukBuku = (TextView) findViewById(R.id.tajukBuku);
        accessionnoBuku = (TextView) findViewById(R.id.accessionnoBuku);
        authorBuku = (TextView) findViewById(R.id.authorBuku);
        commentPositive = (ListView) findViewById(R.id.commentPositive);
        commentNeutral = (ListView) findViewById(R.id.commentNeutral);
        commentNegative = (ListView) findViewById(R.id.commentNegative);

        debug = (TextView) findViewById(R.id.debug);
        // --------------------------------------------------------------

        // --------------------------------------- setOnClickListener ---------------------------------------
        viewPDF.setOnClickListener(this);
        // --------------------------------------------------------------------------------------------------

        // -------------- drawer actions --------------------
        menus = getResources().getStringArray(R.array.menuMain);
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

        book = new Book();
        book.setId(getIntent().getIntExtra("book_id", -1)); // get bundled data from previous activity
        new getBookDetails().execute();

        debug.setText("Book ID: " + book.getId() + "\nBook Title: " + book.getTitle() + "\nBook Author: " + book.getAuthor() + "\nBook Accession No: " + book.getAccessionno() + "\nPDF ID: " + book.getPdfID());
    }

    // ----------------------------- private class AsyncTask -----------------------------
    private class getBookDetails extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(BookDetail.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            // algorithm to retrieve book detail from external database
            // cari detail buku tersebut dalam database
            try {
                // -------------------- send http post request to request for book details data --------------------------------
                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                // retrieve bookDetails
                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> bookDetailData = new ArrayList<NameValuePair>();
                bookDetailData.add(new BasicNameValuePair("book_id", "" + book.getId()));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String bookData = httpHandler.result("http://seladanghijau.netai.net/php/BookDetails.php", bookDetailData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(bookData);
                    JSONArray jArray = jObj.getJSONArray("book_details");

                    JSONObject jsonObjData = jArray.getJSONObject(0);
                    book.setPdfID(jsonObjData.getInt("pdf_id"));
                    book.setAccessionno(jsonObjData.getString("book_accessionno"));
                    book.setAuthor(jsonObjData.getString("book_author"));
                    book.setTitle(jsonObjData.getString("book_title"));
                }

                return true;
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                // ------------------------- set layout -------------------------------
                tajukBuku.setText(book.getTitle());
                authorBuku.setText(book.getAuthor());
                accessionnoBuku.setText("Accession No. : " + book.getAccessionno());
                // --------------------------------------------------------------------
                // --------------------------- setAdapter for listviews ---------------------------------
                // --------------------------------------------------------------------------------------
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }
    // -----------------------------------------------------------------------------------

    // ------------------------------------ OnClick Events ------------------------------------
    public void onClick(View v) { // onClickListener
        switch (v.getId()) {
            case R.id.viewPDF:
                Intent pdfViewer = new Intent(this, PDFViewer.class);

                pdfViewer.putExtra("pdf_id", book.getPdfID());

                startActivity(pdfViewer);
                break;
            case R.id.comment:
                Intent inputComment = new Intent(this, InputComment.class);

                startActivity(inputComment);
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // onItemClickListener
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: // destroy this activity if user clicked back button
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
    // ----------------------------------------------------------------------------------------

    // -------------------------------------- actions for drawer -------------------------------------------------
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
    // ------------------------------------------------------------------------------------------------------------

    // ------------------------------------ Book Class -----------------------------------
    class Book {
        private int id, pdfID;
        private String accessionno, author, title;

        // constructors
        public Book() {
            id = 0;
            pdfID = 0;
            accessionno = null;
            author = null;
            title = null;
        }

        public Book(int id, int pdfID, String accessionno, String author, String title) {
            this.id = id;
            this.pdfID = pdfID;
            this.accessionno = accessionno;
            this.author = author;
            this.title = title;
        }

        // getter and setter
        public void setId(int id) { this.id = id; }
        public void setPdfID(int id) { pdfID = id; }
        public void setAccessionno(String accessionno) { this.accessionno = accessionno;}
        public void setAuthor(String author) { this.author = author; }
        public void setTitle(String title) { this.title = title; }

        public int getId() { return id; }
        public int getPdfID() { return pdfID; }
        public String getAccessionno() { return accessionno; }
        public String getAuthor() { return author; }
        public String getTitle() { return title; }
    }
    // -----------------------------------------------------------------------------------
}
