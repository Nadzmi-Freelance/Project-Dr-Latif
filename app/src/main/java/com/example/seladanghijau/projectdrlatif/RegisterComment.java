package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class RegisterComment extends ActionBarActivity implements View.OnClickListener {
    // elements in activity
    Spinner commentTypeList;
    Button sendComment;
    EditText inputComment;
    ProgressDialog pDialog;

    // other attributes
    String[] commentTypes;
    int commentType, userId, bookId;
    String userType, commentDescription;
    public static final String USER_PREFERENCES = "user_pref";

    // sharedPref
    SharedPreferences userData;
    SharedPreferences.Editor userDataEditor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_comment);

        // ----------------------------------- initialize all elemt in activity ----------------------------------------
        commentTypeList = (Spinner) findViewById(R.id.commentTypeList);
        sendComment = (Button) findViewById(R.id.sendComment);
        inputComment = (EditText) findViewById(R.id.inputComment);

        // element dalam spinner
        commentTypes = getResources().getStringArray(R.array.userType); // get list of userType
        commentTypeList.setAdapter(new ArrayAdapter<String>(RegisterComment.this, android.R.layout.simple_spinner_dropdown_item, commentType));
        // -------------------------------------------------------------------------------------------------------------

        // ------------------------------------- initialize value for variables ---------------------------------------
        userData = getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        userDataEditor = userData.edit();
        // -----------------------------------------------------------------------------------------------------------
    }

    // ------------------------------------------ Click Listener -----------------------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sendComment:
                new SendComment().execute();
                break;
        }
    }
    // ---------------------------------------------------------------------------------------------------------------

    // -------------------------------------- private AsyncTask class ---------------------------------------------
    private class SendComment extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(RegisterComment.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

            // ----------------------------------- initilize variables -----------------------------------------
            commentDescription = inputComment.getText().toString();
            bookId = getIntent().getIntExtra("book_id", -1);
            commentType = getIntent().getIntExtra("comment_type", -1);
            userId = userData.getInt("ID", -1);
            userType = userData.getString("USERTYPE", "");
            // -------------------------------------------------------------------------------------------------
        }

        protected Boolean doInBackground(Void... params) {
            try {
                // -------------------- send http post request to request for book details data --------------------------------
                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                postData.add(new BasicNameValuePair("inBookId", "" + bookId));
                postData.add(new BasicNameValuePair("inUserType", "" + userType));
                postData.add(new BasicNameValuePair("inCommentType", "" + commentType));
                postData.add(new BasicNameValuePair("inUserId", "" + userId));
                postData.add(new BasicNameValuePair("inDescription", "" + commentDescription));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String data = httpHandler.result("http://uitmkedah.net/nadzmi/php/RegisterComment.php", postData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(data);
                    JSONArray jArray = jObj.getJSONArray("result_registercomment");

                    JSONObject jsonObjData = jArray.getJSONObject(0);
                    if(jsonObjData.getString("message").toString().equalsIgnoreCase("success")) {
                        Toast.makeText(RegisterComment.this, "Comment successfully inserted.", Toast.LENGTH_SHORT);
                    } else if(jsonObjData.getString("message").toString().equalsIgnoreCase("error_book_no_record")) {
                        Toast.makeText(RegisterComment.this, "Error, the book was not found.", Toast.LENGTH_LONG);
                        return false;
                    } else if(jsonObjData.getString("message").toString().equalsIgnoreCase("error_insert")) {
                        Toast.makeText(RegisterComment.this, "An error has occurred during the operation. Please try again later.", Toast.LENGTH_LONG);
                        return false;
                    } else if(jsonObjData.getString("message").toString().equalsIgnoreCase("error")) {
                        Toast.makeText(RegisterComment.this, "An error has occurred.", Toast.LENGTH_LONG);
                        return false;
                    } else return false;
                }
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return true;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                // return to previus page
                finish();
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }
    // ------------------------------------------------------------------------------------------------------------
}
