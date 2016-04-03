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

public class LoginPageStudent extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ProgressDialog pDialog;

    // element dalam drawer
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    String[] menus;

    // element dlm activity ni
    TextView register;
    EditText username, password;
    Button login;

    // shared preferences
    SharedPreferences studentData;
    SharedPreferences.Editor studentDataEditor;
    public static final String STUDENT_PREFERENCES = "stud_pref";
    public static final String LOGIN_STAT = "LOGIN_STAT";
    public static final String ID = "ID";
    public static final int LOGGEDIN = 1;
    public static final int NOTLOGGEDIN = 0;

    // other attributes
    String inUsername, inPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_student);

        // --------------- initialize every object ------------------
        // element dlm drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);

        menus = getResources().getStringArray(R.array.menuLogin); // get list of menus from xml file
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer

        // element dlm activity
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        // ----------------------------------------------------------

        // ------------------ implement OnClickListener -------------------
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        // ----------------------------------------------------------------

        // ------------------ get SharedPreference data --------------------
        studentData = getSharedPreferences(STUDENT_PREFERENCES, Context.MODE_PRIVATE);
        studentDataEditor = studentData.edit();
        // -----------------------------------------------------------------

        if(studentData.getInt(LOGIN_STAT, 0) == LOGGEDIN) {
            Intent mainActivity = new Intent(LoginPageStudent.this, MainActivity.class);

            startActivity(mainActivity);
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
            pDialog = new ProgressDialog(LoginPageStudent.this);
            pDialog.setMessage("Tunggu sebentar...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                // -------------------- send http post request to request for book details data --------------------------------
                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                postData.add(new BasicNameValuePair("username", "" + inUsername));
                postData.add(new BasicNameValuePair("password", "" + inPassword));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                String data = httpHandler.result("http://seladanghijau.netai.net/php/loginStudent.php", postData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(data);
                    JSONArray jArray = jObj.getJSONArray("login");

                    // store all data for student in 'student' sharedPreference
                    JSONObject studentJson = jArray.getJSONObject(0);
                    if(studentJson.getInt("student_id") == -1) {
                        return NOTLOGGEDIN;
                    } else {
                        studentDataEditor.putInt(LOGIN_STAT, LOGGEDIN);
                        studentDataEditor.putInt(ID, studentJson.getInt("student_id"));
                        studentDataEditor.commit();

                        return LOGGEDIN;
                    }
                }
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return NOTLOGGEDIN;
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();

            switch(result) {
                case LOGGEDIN:
                    // action when user has successfully logged in
                    Toast.makeText(LoginPageStudent.this, "Logged in", Toast.LENGTH_LONG).show();
                    Intent mainActivity = new Intent(LoginPageStudent.this, MainActivity.class);

                    startActivity(mainActivity);
                    finish();
                    break;
                case NOTLOGGEDIN:
                    // action when user has failed to login
                    Toast.makeText(LoginPageStudent.this, "There are error when logging in. Please try again later.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    // ------------------------------------------------------------------------------------------------------------------

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
