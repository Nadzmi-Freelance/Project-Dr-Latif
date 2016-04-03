package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
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

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    // element dalam drawer
    ProgressDialog pDialog;
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    String[] menus, userType;

    // element dalam activity
    EditText name, email, username, password;
    TextView gotoLogin;
    Button register;
    Spinner userTypeList;

    // other attributes
    String inUserType, inUsername, inPassword, inName, inEmail;
    String registerResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

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

        // element dalam activity
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        gotoLogin = (TextView) findViewById(R.id.gotoLogin);
        register = (Button) findViewById(R.id.register);
        userTypeList = (Spinner) findViewById(R.id.userTypeList);

        // element dalam spinner
        userType = getResources().getStringArray(R.array.userType); // get list of userType
        userTypeList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, userType));
        // ----------------------------------------------------------

        // ----------------------- implement OnClickListener ---------------------
        gotoLogin.setOnClickListener(this);
        register.setOnClickListener(this);
        // -----------------------------------------------------------------------
    }

    // --------------------------- OnClickListener & OnItemClickListener -----------------------------------------
    // OnClick for element in activity
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gotoLogin:
                // goto login page
                Intent loginPageStudent = new Intent(RegisterPage.this, LoginPageStudent.class);

                startActivity(loginPageStudent);
                finish();
                break;
            case R.id.register:
                // register the user
                new Register().execute();
                break;
        }
    }

    // OnClick for item in menu drawer
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
        }
    }
    // ------------------------------------------------------------------------------------------------------------

    // ------------------------------------- AsyncTask private class ----------------------------------------------
    private class Register extends AsyncTask<Void, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(RegisterPage.this);
            pDialog.setMessage("Tunggu sebentar...");
            pDialog.setCancelable(false);
            pDialog.show();

            inUserType = userTypeList.getSelectedItem().toString();
            inUsername = username.getText().toString();
            inPassword = password.getText().toString();
            inName = name.getText().toString();
            inEmail = email.getText().toString();

            if(inUserType.equalsIgnoreCase("student"))
                inUserType = "student";
            else if(inUserType.equalsIgnoreCase("staff"))
                inUserType = "staff";
            else if(inUserType.equalsIgnoreCase("stafflibrary"))
                inUserType = "stafflibrary";
        }

        protected Integer doInBackground(Void... params) {
            if(inUsername != null)
                if(inPassword != null)
                    if(inName != null)
                        if(inEmail != null) {
                            try {
                                // -------------------- send http post request to request for book details data --------------------------------
                                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                                // ------------------------------ setup data for the post request ----------------------------------------------
                                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                                postData.add(new BasicNameValuePair("userType", "" + inUserType));
                                postData.add(new BasicNameValuePair("name", "" + inName));
                                postData.add(new BasicNameValuePair("email", "" + inEmail));
                                postData.add(new BasicNameValuePair("username", "" + inUsername));
                                postData.add(new BasicNameValuePair("password", "" + inPassword));
                                // -------------------------------------------------------------------------------------------------------------

                                // ------------------ retrieve the requested data -------------------------------------------
                                // get the result from http post
                                String data = httpHandler.result("http://seladanghijau.netai.net/php/register.php", postData);

                                if (httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                                    // retrieve data from JSON string
                                    JSONObject jObj = new JSONObject(data);
                                    JSONArray jArray = jObj.getJSONArray("register");

                                    // store all data for student in 'student' sharedPreference
                                    JSONObject studentJson = jArray.getJSONObject(0);
                                    registerResult = studentJson.getString("result");

                                    if (registerResult.equalsIgnoreCase("success"))
                                        return 1;
                                    else if (registerResult.equalsIgnoreCase("existed"))
                                        return 2;
                                    else if (registerResult.equalsIgnoreCase("error_check"))
                                        return 0;
                                }
                                // -------------------------------------------------------------------------------------------
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else
                            return 3;
                    else
                        return 3;
                else
                    return 3;
            else
                return 3;

            return 0;
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();

            switch(result) {
                case 0:
                    Toast.makeText(RegisterPage.this, "Failed to register. An error occured.", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Intent loginPageStudent = new Intent(RegisterPage.this, LoginPageStudent.class);

                    startActivity(loginPageStudent);
                    finish();
                    break;
                case 2:
                    Toast.makeText(RegisterPage.this, "Failed to register. Account already existed", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(RegisterPage.this, "Please fill up all of the fields.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    // -----------------------------------------------------------------------------------------------------------------------

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
