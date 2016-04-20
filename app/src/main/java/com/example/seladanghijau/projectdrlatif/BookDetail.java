package com.example.seladanghijau.projectdrlatif;

import android.app.Activity;
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
import org.apache.http.impl.cookie.BasicCommentHandler;
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
    Button viewPDF, registerComment;
    TextView tajukBuku, accessionnoBuku, authorBuku;
    ListView commentNeutral, commentPositive, commentNegative;
    String[] menus;

    // data from other activities
    Book book;
    List<Comment>[] commentList = new List[3];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // -------------- register android objects ----------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        viewPDF = (Button) findViewById(R.id.viewPDF);
        registerComment = (Button) findViewById(R.id.registerComment);
        tajukBuku = (TextView) findViewById(R.id.tajukBuku);
        accessionnoBuku = (TextView) findViewById(R.id.accessionnoBuku);
        authorBuku = (TextView) findViewById(R.id.authorBuku);
        commentPositive = (ListView) findViewById(R.id.commentPositive);
        commentNeutral = (ListView) findViewById(R.id.commentNeutral);
        commentNegative = (ListView) findViewById(R.id.commentNegative);
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
        new RetrieveComment().execute();
    }

    // ----------------------------- private class AsyncTask -----------------------------
    private class getBookDetails extends AsyncTask<Void, Void, Boolean> {
        int book_pdfID;
        String book_accessionno, book_author, book_title;

        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(BookDetail.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
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
                bookDetailData.add(new BasicNameValuePair("inBookId", "" + book.getId()));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String bookData = httpHandler.result("http://uitmkedah.net/nadzmi/php/BookDetails.php", bookDetailData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(bookData);
                    JSONArray jArray = jObj.getJSONArray("book_details");

                    JSONObject jsonObjData = jArray.getJSONObject(0);
                    if(jsonObjData.getString("message").toString().equalsIgnoreCase("success")) {
                        book_pdfID = jsonObjData.getInt("pdf_id");
                        book_accessionno = jsonObjData.getString("book_accessionno");
                        book_author = jsonObjData.getString("book_author");
                        book_title = jsonObjData.getString("book_title");
                    }
                } else return false;
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return true;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                book.setAccessionno(book_accessionno);
                book.setPdfID(book_pdfID);
                book.setAuthor(book_author);
                book.setTitle(book_title);

                // ------------------------- set layout -------------------------------
                tajukBuku.setText(book.getTitle());
                authorBuku.setText(book.getAuthor());
                accessionnoBuku.setText("Accession No. : " + book.getAccessionno());
                // --------------------------------------------------------------------
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    class RetrieveComment extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(BookDetail.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
                try {
                // -------------------------------------- algorithm to retrieve comments ------------------------------------------
                for(int a=0 ; a<3 ; a++) {
                    int commentType = a+1;
                    HTTPHandler httpHandler2 = new HTTPHandler();

                    commentList[a] = new ArrayList<>();

                    // ------------------------------ setup data for the post request ----------------------------------------------
                    List<NameValuePair> commentPostData = new ArrayList<NameValuePair>();
                    commentPostData.add(new BasicNameValuePair("inBookId", "" + book.getId()));
                    commentPostData.add(new BasicNameValuePair("inCommentType", "" + commentType));
                    // -------------------------------------------------------------------------------------------------------------

                    // ------------------ retrieve the requested data -------------------------------------------
                    // get the result from http post
                    String commentData = httpHandler2.result("http://uitmkedah.net/nadzmi/php/RetrieveComment.php", commentPostData);

                    if(httpHandler2.getStatus() == HttpURLConnection.HTTP_OK) {
                        // retrieve data from JSON string
                        JSONObject jObj = new JSONObject(commentData);
                        JSONArray jArray = jObj.getJSONArray("comments");

                        for(int x=0 ; x<jArray.length() ; x++) {
                            JSONObject jsonObject = jArray.getJSONObject(x);

                            if(jsonObject.getString("message").toString().equalsIgnoreCase("success")) {
                                String username = jsonObject.getString("user_name");
                                String usercomment = jsonObject.getString("user_comment");

                                commentList[a].add(new Comment(username, usercomment));
                            }
                        }
                    } else return false;
                }
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return true;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                // --------------------------- setAdapter for listviews ---------------------------------
                commentPositive.setAdapter(new CommentAdapter(BookDetail.this, commentList[0]));
                commentNegative.setAdapter(new CommentAdapter(BookDetail.this, commentList[1]));
                commentNeutral.setAdapter(new CommentAdapter(BookDetail.this, commentList[2]));
                // --------------------------------------------------------------------------------------
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }    // -----------------------------------------------------------------------------------

    // ------------------------------------ OnClick Events ------------------------------------
    public void onClick(View v) { // onClickListener
        switch (v.getId()) {
            case R.id.viewPDF:
                Intent pdfViewer = new Intent(this, PDFViewer.class);

                pdfViewer.putExtra("pdf_id", book.getPdfID());

                startActivity(pdfViewer);
                break;
            case R.id.registerComment:
                Intent inputComment = new Intent(this, RegisterComment.class);

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

    // ------------------------------------ Adapter Classes -----------------------------------
    class Comment {
        private String username, usercomment;

        public Comment(String username, String usercomment) {
            this.username = username;
            this.usercomment = usercomment;
        }

        public void setUsername(String username) { this.username = username; }
        public void setUsercomment(String usercomment) { this.usercomment = usercomment; }

        public String getUsername() { return username; }
        public String getUsercomment() { return usercomment; }
    }

    class CommentHolder {
        public TextView username, usercomment;

        public CommentHolder(View base) {
            username = (TextView) base.findViewById(R.id.username);
            usercomment = (TextView) base.findViewById(R.id.usercomment);
        }
    }

    class CommentAdapter extends BaseAdapter {
        private List<Comment> commentList;
        private Activity context;
        private LayoutInflater layoutInflater;

        public CommentAdapter(Activity context, List<Comment> commentList) {
            this.context = context;
            this.commentList = commentList;

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() { return commentList.size(); }
        public Object getItem(int position) { return commentList.get(position); }
        public long getItemId(int position) { return position; }

        public View getView(int position, View convertView, ViewGroup parent) {
            CommentHolder commentHolder;
            View v = convertView;

            if(convertView == null) {
                LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = li.inflate(R.layout.comment_layout, null);
                commentHolder = new CommentHolder(v);
                v.setTag(commentHolder);
            } else {
                commentHolder = (CommentHolder) v.getTag();
            }

            commentHolder.username.setText(commentList.get(position).getUsername());
            commentHolder.usercomment.setText(commentList.get(position).getUsercomment());

            return v;
        }
    }
    // -----------------------------------------------------------------------------------
}
