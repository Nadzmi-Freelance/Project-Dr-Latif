package com.example.seladanghijau.projectdrlatif;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class RetrieveComments extends ActionBarActivity {
    // elements in activity
    ListView commentList;
    ProgressDialog pDialog;

    // other attributes
    List<Comment> commentItemList = new ArrayList<>();
    String commentType;
    int bookId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_comments);

        // initialize activity elements
        commentList = (ListView) findViewById(R.id.commentList);

        // get intent extra data
        commentType = getIntent().getStringExtra("comment_type");
        bookId = getIntent().getIntExtra("book_id", -1);

        // start retrieve comments
        new RetrieveComment().execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrieve_comments, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: // destroy this activity if user clicked back button
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public class RetrieveComment extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(RetrieveComments.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            try {
                // -------------------------------------- algorithm to retrieve comments ------------------------------------------
                HTTPHandler httpHandler2 = new HTTPHandler();

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> commentPostData = new ArrayList<NameValuePair>();
                commentPostData.add(new BasicNameValuePair("inBookId", "" + bookId));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String commentData = httpHandler2.result("http://uitmkedah.net/nadzmi/php/RetrieveComment.php", commentPostData);

                if(httpHandler2.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(commentData);
                    JSONArray jArray = jObj.getJSONArray(commentType);

                    for(int x=0 ; x<jArray.length() ; x++) {
                        JSONObject jsonObject = jArray.getJSONObject(x);

                        if(jsonObject.getString("message").toString().equalsIgnoreCase("success")) {
                            String username = jsonObject.getString("user_name");
                            String usercomment = jsonObject.getString("user_comment");
                            commentItemList.add(new Comment(username, usercomment));
                        } else if(jsonObject.getString("message").toString().equalsIgnoreCase("no_record")) {
                            String username = "";
                            String usercomment = "No one commented on this book yet. Be the first to comment.";
                            commentItemList.add(new Comment(username, usercomment));

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
                commentList.setAdapter(new CommentAdapter(RetrieveComments.this, commentItemList));
                // --------------------------------------------------------------------------------------
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    // ------------------------------------ other classes ----------------------------------------------------
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
    // -------------------------------------------------------------------------------------------------------
}
