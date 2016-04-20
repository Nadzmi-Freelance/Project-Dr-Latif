package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
    ProgressDialog pDialog;
    Spinner commentTypeList;
    Button registerComment;
    EditText inputComment;

    // shared pref data
    SharedPreferences userData;
    SharedPreferences.Editor userDataEditor;

    // other attributes
    String[] commentType;
    String inCommentType, inUserType, inDescription;
    int inBookId, inUserId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_comment);

        // ----------------------------------- initialize all elemt in activity ----------------------------------------
        commentTypeList = (Spinner) findViewById(R.id.commentTypeList);
        registerComment = (Button) findViewById(R.id.registerComment);
        inputComment = (EditText) findViewById(R.id.inputComment);

        // element dalam spinner
        commentType = getResources().getStringArray(R.array.userType); // get list of userType
        commentTypeList.setAdapter(new ArrayAdapter<String>(RegisterComment.this, android.R.layout.simple_spinner_dropdown_item, commentType));
        // -------------------------------------------------------------------------------------------------------------

        // ----------------------------------------- initialize var --------------------------------------------
        userData = this.getPreferences(Context.MODE_PRIVATE);
        userDataEditor = userData.edit();
        // -----------------------------------------------------------------------------------------------------
    }

    // ------------------------------------------ Click Listener -----------------------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.registerComment:
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

            // initialize all required varibales
            inCommentType = commentTypeList.getSelectedItem().toString();
            inUserType = userData.getString("TYPE", "STUDENT");
            inDescription = inputComment.getText().toString();
            inBookId = getIntent().getIntExtra("book_id", -1);
            inUserId = userData.getInt("ID", -1);
        }

        protected Boolean doInBackground(Void... params) {
            try {
                // -------------------- send http post request to request for book details data --------------------------------
                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                // retrieve bookDetails
                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> commentDetailsData = new ArrayList<NameValuePair>();
                commentDetailsData.add(new BasicNameValuePair("inBookID", "" + inBookId));
                commentDetailsData.add(new BasicNameValuePair("inUserType", "" + inUserType));
                commentDetailsData.add(new BasicNameValuePair("inCommentType", "" + inCommentType));
                commentDetailsData.add(new BasicNameValuePair("inUserID", "" + inUserId));
                commentDetailsData.add(new BasicNameValuePair("inDescription", "" + inDescription));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String bookData = httpHandler.result("http://uitmkedah.net/nadzmi/php/RegisterComment.php", commentDetailsData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(bookData);
                    JSONArray jArray = jObj.getJSONArray("result_registercomment");

                    JSONObject jsonObjData = jArray.getJSONObject(0);
                    if(jsonObjData.getString("message").toString().equalsIgnoreCase("success"))
                        return true;
                    else
                        return false;
                }
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result) {
                Toast.makeText(RegisterComment.this, "Successfully commented the book", Toast.LENGTH_SHORT).show();

                Intent bookDetail = new Intent(RegisterComment.this, BookDetail.class);
                bookDetail.putExtra("book_id", inBookId);

                startActivity(bookDetail);
                finish();
            } else {
                Toast.makeText(RegisterComment.this, "An error has occurred", Toast.LENGTH_LONG).show();
            }

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }
    // ------------------------------------------------------------------------------------------------------------
}
