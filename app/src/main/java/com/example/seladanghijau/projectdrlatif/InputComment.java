package com.example.seladanghijau.projectdrlatif;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InputComment extends ActionBarActivity implements View.OnClickListener {
    // elements in activity
    Spinner commentTypeList;
    Button sendComment;
    EditText inputComment;

    // other attributes
    String[] commentType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_comment);

        // ----------------------------------- initialize all elemt in activity ----------------------------------------
        commentTypeList = (Spinner) findViewById(R.id.commentTypeList);
        sendComment = (Button) findViewById(R.id.sendComment);
        inputComment = (EditText) findViewById(R.id.inputComment);

        // element dalam spinner
        commentType = getResources().getStringArray(R.array.userType); // get list of userType
        commentTypeList.setAdapter(new ArrayAdapter<String>(InputComment.this, android.R.layout.simple_spinner_dropdown_item, commentType));
        // -------------------------------------------------------------------------------------------------------------
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
        }

        protected Boolean doInBackground(Void... params) {
            return null;
        }

        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
    // ------------------------------------------------------------------------------------------------------------
}
