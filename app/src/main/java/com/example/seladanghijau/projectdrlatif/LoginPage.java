package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    // element dlm activity ni
    ProgressDialog pDialog;
    TextView register;
    EditText username, password;
    Button login;
    Spinner userTypeList;
    String[] userType;

    // shared preferences
    SharedPreferences userData;
    SharedPreferences.Editor userDataEditor;
    public static final String USER_PREFERENCES = "user_pref";
    public static final String LOGIN_STAT = "LOGIN_STAT";
    public static final int LOGGEDIN = 1;
    public static final int NOTLOGGEDIN = 0;

    // other attributes
    String inUsername, inPassword, inUserType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_student);

        // --------------- initialize every object ------------------
        // element dlm activity
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        userTypeList = (Spinner) findViewById(R.id.userTypeList);

        // element dalam spinner
        userType = getResources().getStringArray(R.array.userType); // get list of userType
        userTypeList.setAdapter(new ArrayAdapter<String>(LoginPage.this, android.R.layout.simple_spinner_dropdown_item, userType));
        // ----------------------------------------------------------

        // ------------------ implement OnClickListener -------------------
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        // ----------------------------------------------------------------

        // ------------------ get SharedPreference data --------------------
        userData = getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        userDataEditor = userData.edit();
        // -----------------------------------------------------------------

        if(userData.getInt(LOGIN_STAT, 0) == LOGGEDIN) {
            Intent searchBook = new Intent(LoginPage.this, SearchBook.class);

            startActivity(searchBook);
            finish();
        }
    }

    // --------------------------- OnClickListener & OnItemClickListener -----------------------------------------
    // OnClickListener utk item dlm activity
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.login:
                // login the user and make a session
                inUsername = username.getText().toString().trim();
                inPassword = password.getText().toString().trim();
                inUserType = userTypeList.getSelectedItem().toString().trim();

                if(inUserType.equalsIgnoreCase("Student"))
                    inUserType = "student";
                else if(inUserType.equalsIgnoreCase("Staff"))
                    inUserType = "staff";
                else if(inUserType.equalsIgnoreCase("Library Staff"))
                    inUserType = "stafflibrary";

                new LoginStudent().execute();
                break;
            case R.id.register:
                // goto register page
                Intent registerPage = new Intent(this, RegisterPage.class);

                startActivity(registerPage);
                finish();
                break;
        }
    }

    // OnClickListener utk item dalam drawer
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
        }
    }
    // ---------------------------------------------------------------------------------------------------------------

    // ------------------------------------- AsyncTask private class ----------------------------------------------
    private class LoginStudent extends AsyncTask<Void, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(LoginPage.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                // -------------------- send http post request to request for book details data --------------------------------
                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                postData.add(new BasicNameValuePair("inUsername", "" + inUsername));
                postData.add(new BasicNameValuePair("inPassword", "" + inPassword));
                postData.add(new BasicNameValuePair("inUserType", "" + inUserType));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String data = httpHandler.result("http://uitmkedah.net/nadzmi/php/login.php", postData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(data);
                    JSONArray jArray = jObj.getJSONArray("login");

                    // store all data for student in 'student' sharedPreference
                    JSONObject userJson = jArray.getJSONObject(0);
                    if(userJson.getInt("user_id") > 0) {
                        userDataEditor.putInt(LOGIN_STAT, LOGGEDIN);
                        userDataEditor.putInt("ID", userJson.getInt("user_id"));
                        userDataEditor.putString("TYPE", userJson.getString("user_type"));
                        userDataEditor.putString("NAME", userJson.getString("user_name"));
                        userDataEditor.putString("USERNAME", userJson.getString("user_username"));
                        userDataEditor.putString("PASSWORD", userJson.getString("user_password"));
                        userDataEditor.putString("USERTYPE", userJson.getString("user_type"));
                        userDataEditor.commit();

                        return 0;
                    } else if(userJson.getInt("user_id") == -1) {
                        userDataEditor.putInt(LOGIN_STAT, NOTLOGGEDIN);
                        userDataEditor.commit();

                        return -1;
                    } else if(userJson.getInt("user_id") <= -2) {
                        userDataEditor.putInt(LOGIN_STAT, NOTLOGGEDIN);
                        userDataEditor.commit();

                        return -2;
                    }
                }
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            userDataEditor.putInt(LOGIN_STAT, NOTLOGGEDIN);
            userDataEditor.commit();

            return -2;
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();

            switch(result) {
                case 0:
                    // action when user has successfully logged in
                    Toast.makeText(LoginPage.this, "Logged in", Toast.LENGTH_LONG).show();

                    Intent searchBook = new Intent(LoginPage.this, SearchBook.class);
                    startActivity(searchBook);
                    finish();
                    break;
                case -1:
                    Toast.makeText(LoginPage.this, "Incorrect username or password.", Toast.LENGTH_LONG).show();
                    break;
                case -2:
                    // action when user has failed to login
                    Toast.makeText(LoginPage.this, "There are error when logging in. Please try again later.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    // ------------------------------------------------------------------------------------------------------------------
}
