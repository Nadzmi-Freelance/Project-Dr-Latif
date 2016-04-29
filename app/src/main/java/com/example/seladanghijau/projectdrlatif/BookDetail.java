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
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class BookDetail extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    // elements in activity
    ProgressDialog pDialog;
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    TabHost tabHost;
    Button btnRegCommentPositive, btnRegCommentNegative, btnRegCommentNeutral;
    Button viewPDF, viewPositiveComment, viewNeutralComment, viewNegativeComment;
    TextView tajukBuku, accessionnoBuku, authorBuku;
    ListView commentNeutral, commentPositive, commentNegative;
    String[] menus;

    // other data
    TabHost.TabSpec positiveTabSpec, negativeTabSpec, neutralTabSpec;
    JSONArray jArrayPositive, jArrayNeutral, jArrayNegative;

    // data from other activities
    Book book;
    List<Comment> positiveCommentList, neutralCommentList, negativeCommentList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // -------------- register android objects ----------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);
        tabHost = (TabHost) findViewById(R.id.tabHost);
        viewPDF = (Button) findViewById(R.id.viewPDF);
        btnRegCommentPositive = (Button) findViewById(R.id.btnRegCommentPositive);
        btnRegCommentNeutral = (Button) findViewById(R.id.btnRegCommentNeutral);
        btnRegCommentNegative = (Button) findViewById(R.id.btnRegCommentNegative);
        viewPositiveComment = (Button) findViewById(R.id.viewPositiveComment);
        viewNeutralComment = (Button) findViewById(R.id.viewNeutralComment);
        viewNegativeComment = (Button) findViewById(R.id.viewNegativeComment);
        tajukBuku = (TextView) findViewById(R.id.tajukBuku);
        accessionnoBuku = (TextView) findViewById(R.id.accessionnoBuku);
        authorBuku = (TextView) findViewById(R.id.authorBuku);
        commentPositive = (ListView) findViewById(R.id.commentPositive);
        commentNeutral = (ListView) findViewById(R.id.commentNeutral);
        commentNegative = (ListView) findViewById(R.id.commentNegative);
        // --------------------------------------------------------------

        // --------------------------------------- setOnClickListener ---------------------------------------
        viewPDF.setOnClickListener(this);
        btnRegCommentPositive.setOnClickListener(this);
        btnRegCommentNeutral.setOnClickListener(this);
        btnRegCommentNegative.setOnClickListener(this);
        viewPositiveComment.setOnClickListener(this);
        viewNeutralComment.setOnClickListener(this);
        viewNegativeComment.setOnClickListener(this);
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
        positiveTabSpec = tabHost.newTabSpec("Tab One");
        negativeTabSpec = tabHost.newTabSpec("Tab Two");
        neutralTabSpec = tabHost.newTabSpec("Tab Three");

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

    // ------------------------------------ OnClick Events ------------------------------------
    public void onClick(View v) { // onClickListener
        switch (v.getId()) {
            case R.id.viewPDF:
                Intent pdfViewer = new Intent(this, PDFViewer.class);

                pdfViewer.putExtra("pdf_id", book.getPdfID());

                startActivity(pdfViewer);
                break;
            case R.id.viewPositiveComment:
                Intent viewPositiveComment = new Intent(this, RetrieveComments.class);

                viewPositiveComment.putExtra("book_id", book.getId());
                viewPositiveComment.putExtra("comment_type", "positive_comments");

                startActivity(viewPositiveComment);
                break;
            case R.id.viewNeutralComment:
                Intent viewNeutralComment = new Intent(this, RetrieveComments.class);

                viewNeutralComment.putExtra("book_id", book.getId());
                viewNeutralComment.putExtra("comment_type", "neutral_comments");

                startActivity(viewNeutralComment);
                break;
            case R.id.viewNegativeComment:
                Intent viewNegativeComment = new Intent(this, RetrieveComments.class);

                viewNegativeComment.putExtra("book_id", book.getId());
                viewNegativeComment.putExtra("comment_type", "negative_comments");

                startActivity(viewNegativeComment);
                break;
            case R.id.btnRegCommentPositive:
                Intent regPosComment = new Intent(this, RegisterComment.class);
                regPosComment.putExtra("comment_type", 1);
                regPosComment.putExtra("book_id", book.getId());

                startActivity(regPosComment);
                break;
            case R.id.btnRegCommentNeutral:
                Intent regNeuComment = new Intent(this, RegisterComment.class);
                regNeuComment.putExtra("comment_type", 3);
                regNeuComment.putExtra("book_id", book.getId());

                startActivity(regNeuComment);
                break;
            case R.id.btnRegCommentNegative:
                Intent regNegComment = new Intent(this, RegisterComment.class);
                regNegComment.putExtra("comment_type", 2);
                regNegComment.putExtra("book_id", book.getId());

                startActivity(regNegComment);
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // onItemClickListener
        switch (parent.getId()) {
            case R.id.menuList:
                menuList.setItemChecked(position, true);
                getSupportActionBar().setTitle(menus[position]);

                drawerLayout.closeDrawers();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }
    // -----------------------------------------------------------------------------------

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: // destroy this activity if user clicked back button
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

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
    // ----------------------------------------------------------------------------------------

    public boolean onOptionsItemSelected(MenuItem item) { // handle action bar item click(top bar)
        return super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig) { // detect when the configuration(landscape or portrait) of the screen change
        super.onConfigurationChanged(newConfig);

        drawerListener.onConfigurationChanged(newConfig); // change to new configuration
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
                List<NameValuePair> bookDetailData = new ArrayList<>();
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
                    if(jsonObjData.getString("message").equalsIgnoreCase("success")) {
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
                HTTPHandler httpHandler2 = new HTTPHandler();

                positiveCommentList = new ArrayList<>();
                neutralCommentList = new ArrayList<>();
                negativeCommentList = new ArrayList<>();

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> commentPostData = new ArrayList<>();
                commentPostData.add(new BasicNameValuePair("inBookId", "" + book.getId()));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String commentData = httpHandler2.result("http://uitmkedah.net/nadzmi/php/RetrieveComment.php", commentPostData);

                if(httpHandler2.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(commentData);
                    jArrayPositive = jObj.getJSONArray("positive_comments");
                    jArrayNeutral = jObj.getJSONArray("neutral_comments");
                    jArrayNegative = jObj.getJSONArray("negative_comments");

                    for(int x=0 ; x<jArrayPositive.length() ; x++) {
                        JSONObject jsonObject = jArrayPositive.getJSONObject(x);

                        if(jsonObject.getString("message").equalsIgnoreCase("success")) {
                            String username = jsonObject.getString("user_name");
                            String usercomment = jsonObject.getString("user_comment");
                            positiveCommentList.add(new Comment(username, usercomment));
                        } else if(jsonObject.getString("message").equalsIgnoreCase("no_record")) {
                            String username = "";
                            String usercomment = "No one commented on this book yet. Be the first to comment.";
                            positiveCommentList.add(new Comment(username, usercomment));

                            break;
                        } else return false;
                    }

                    for(int x=0 ; x<jArrayNeutral.length() ; x++) {
                        JSONObject jsonObject = jArrayNeutral.getJSONObject(x);

                        if(jsonObject.getString("message").equalsIgnoreCase("success")) {
                            String username = jsonObject.getString("user_name");
                            String usercomment = jsonObject.getString("user_comment");
                            neutralCommentList.add(new Comment(username, usercomment));
                        } else if(jsonObject.getString("message").equalsIgnoreCase("no_record")) {
                            String username = "";
                            String usercomment = "No one commented on this book yet. Be the first to comment.";
                            positiveCommentList.add(new Comment(username, usercomment));

                            break;
                        } else return false;
                    }

                    for(int x=0 ; x<jArrayNegative.length() ; x++) {
                        JSONObject jsonObject = jArrayNegative.getJSONObject(x);

                        if(jsonObject.getString("message").equalsIgnoreCase("success")) {
                            String username = jsonObject.getString("user_name");
                            String usercomment = jsonObject.getString("user_comment");
                            negativeCommentList.add(new Comment(username, usercomment));
                        } else if(jsonObject.getString("message").equalsIgnoreCase("no_record")) {
                            String username = "";
                            String usercomment = "No one commented on this book yet. Be the first to comment.";
                            positiveCommentList.add(new Comment(username, usercomment));

                            break;
                        } else return false;
                    }
                } else return false;
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return true;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                // --------------------------- setAdapter for listviews ---------------------------------
                positiveTabSpec.setIndicator("Positive (" + jArrayPositive.length() + ")");
                neutralTabSpec.setIndicator("Neutral (" + jArrayNeutral.length() + ")");
                negativeTabSpec.setIndicator("Negative (" + jArrayNegative.length() + ")");

                commentPositive.setAdapter(new CommentAdapter(BookDetail.this, positiveCommentList));
                commentNeutral.setAdapter(new CommentAdapter(BookDetail.this, neutralCommentList));
                commentNegative.setAdapter(new CommentAdapter(BookDetail.this, negativeCommentList));
                // --------------------------------------------------------------------------------------
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }
    // ------------------------------------------------------------------------------------------------------------

    // ------------------------------------ Other Classes -----------------------------------
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

        public int getId() {
            return id;
        }

        // getter and setter
        public void setId(int id) { this.id = id; }

        public int getPdfID() { return pdfID; }

        public void setPdfID(int pdfID) {
            this.pdfID = pdfID;
        }

        public String getAccessionno() { return accessionno; }

        public void setAccessionno(String accessionno) {
            this.accessionno = accessionno;
        }

        public String getAuthor() { return author; }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() { return title; }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    class Comment {
        private String username, usercomment;

        public Comment(String username, String usercomment) {
            this.username = username;
            this.usercomment = usercomment;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) { this.username = username; }

        public String getUsercomment() { return usercomment; }

        public void setUsercomment(String usercomment) {
            this.usercomment = usercomment;
        }
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
