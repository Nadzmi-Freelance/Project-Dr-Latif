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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class UserProfile extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    // element in activity
    Button logout;
    ProgressDialog pDialog;
    TextView txtUsername, txtName, txtAddress, txtPosition, txtDepartment, txtEmail, txtPhoneNo;

    // element dalam drawer
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    String[] menus;

    // sharedpreference data
    User user;
    SharedPreferences userData;
    SharedPreferences.Editor userDataEditor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // ---------------------- setup element in activity ---------------------------
        logout = (Button) findViewById(R.id.logout);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtName = (TextView) findViewById(R.id.txtName);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtPosition = (TextView) findViewById(R.id.txtPosition);
        txtDepartment = (TextView) findViewById(R.id.txtDepartment);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPhoneNo = (TextView) findViewById(R.id.txtPhoneNo);
        // ----------------------------------------------------------------------------

        // ------------------- setup element in Drawer ----------------------------
        // element dlm drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuList = (ListView) findViewById(R.id.menuList);

        menus = getResources().getStringArray(R.array.menuLogin); // get list of menus from xml file

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set back arrow icon on left, up most of the screen

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus)); // set a list of menus for the menu drawer
        // ------------------------------------------------------------------------

        // ------------------------- setup sharedpreference ----------------------------------
        userData = getSharedPreferences(LoginPage.USER_PREFERENCES, MODE_PRIVATE);
        userDataEditor = userData.edit();
        userDataEditor.apply();
        // -----------------------------------------------------------------------------------

        // ---------------------- Setup OnClickListener in activity -----------------------------
        logout.setOnClickListener(this);
        // --------------------------------------------------------------------------------------

        // --------------------------- setup OnItemClickListener in drawer ----------------------------
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0); // declare listener for drawer menu
        drawerLayout.setDrawerListener(drawerListener); // register listener for drawer menu

        menuList.setOnItemClickListener(this); // register click listener for each of the menus of the menu drawer
        // --------------------------------------------------------------------------------------------

        // -------------- drawer actions --------------------
        menus = getResources().getStringArray(R.array.menuMain);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(drawerListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus));
        menuList.setOnItemClickListener(this);
        // -------------------------------------------------

        // setup variables
        user = new User();
        user.setId(userData.getInt("ID", -1));
        user.setType(userData.getString("USERTYPE", "student"));

        new GetProfile().execute();
    }

    // --------------------------- OnClickListener & OnItemClickListener -----------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.logout:
                userDataEditor.clear();
                userDataEditor.putInt(LoginPage.LOGIN_STAT, LoginPage.NOTLOGGEDIN);
                userDataEditor.commit();

                Intent loginPage = new Intent(UserProfile.this, LoginPage.class);
                startActivity(loginPage);
                finish();
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
        }
    }
    // -----------------------------------------------------------------------------------------------------------

    // ------------------------------------------ actions for drawer ----------------------------------------------
    protected void onPostCreate(Bundle savedInstanceState) { // used for syncing the state of the icon on left, up most of the screen
        super.onPostCreate(savedInstanceState);

        drawerListener.syncState(); // syncing the state of the icon on left, up most of the screen
    }
    // ------------------------------------------------------------------------------------------------------------

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // -------------------------------------------------------------------------------------------------------------

    public boolean onOptionsItemSelected(MenuItem item) { // handle action bar item click(top bar)
        return drawerListener.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig) { // detect when the configuration(landscape or portrait) of the screen change
        super.onConfigurationChanged(newConfig);

        drawerListener.onConfigurationChanged(newConfig); // change to new configuration
    }

    // ------------------------------------- AsyncTask private class ----------------------------------------------
    private class GetProfile extends AsyncTask<Void, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(UserProfile.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected Integer doInBackground(Void... params) {
            try {
                HTTPHandler httpHandler = new HTTPHandler();

                List<NameValuePair> postData = new ArrayList<>();
                postData.add(new BasicNameValuePair("inUserId", "" + user.getId()));
                postData.add(new BasicNameValuePair("inUserType", user.getType()));

                String responseData = httpHandler.result("http://uitmkedah.net/nadzmi/php/UserProfile.php", postData);

                if (httpHandler.getStatus() == HttpURLConnection.HTTP_OK) { // http request "OK": successfully connect to database
                    JSONObject jObj = new JSONObject(responseData);
                    JSONArray jArray = jObj.getJSONArray("user_profile");

                    JSONObject tempJSON = jArray.getJSONObject(0);
                    // get data from JSON(elementarily)
                    if (tempJSON.getString("message").equalsIgnoreCase("success")) {
                        user.setName(tempJSON.getString("user_name"));
                        user.setAddress(tempJSON.getString("user_address"));
                        user.setEmail(tempJSON.getString("user_email"));
                        user.setPhoneno(tempJSON.getString("user_phoneno"));
                        user.setPosition(tempJSON.getString("user_position"));
                        user.setDepartment(tempJSON.getString("user_department"));
                        user.setUsername(tempJSON.getString("user_username"));
                        user.setPassword(tempJSON.getString("user_password"));

                        return 1;
                    } else if (tempJSON.getString("message").equalsIgnoreCase("error_norecord")) {
                        return 2;
                    } else if (tempJSON.getString("message").equalsIgnoreCase("error_server")) {
                        return 3;
                    } else if (tempJSON.getString("message").equalsIgnoreCase("error")) {
                        return 4;
                    } else return 4;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 4;
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            switch (result) {
                case 1: // success
                    txtUsername.setText("Username: " + user.getUsername());
                    txtName.setText("Name: " + user.getName());
                    txtPosition.setText("Position: " + user.getPosition());
                    txtDepartment.setText("Department: " + user.getDepartment());
                    txtEmail.setText("E-Mail: " + user.getEmail());
                    txtPhoneNo.setText("Phone No.: " + user.getPhoneno());
                    txtAddress.setText("Address: " + user.getAddress());
                    break;
                case 2: // no record found
                    Toast.makeText(UserProfile.this, "Your record does not exist in the database. Please login again.", Toast.LENGTH_LONG).show();
                    break;
                case 3: // error in server
                    Toast.makeText(UserProfile.this, "An error has occurred in the server. Plase contact your administrator.", Toast.LENGTH_LONG).show();
                    break;
                case 4: // other error
                    Toast.makeText(UserProfile.this, "An error has occurred.", Toast.LENGTH_LONG).show();
                    break;
            }

            // dismiss progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    // -------------------------------------------- other classes --------------------------------------------------
    class User {
        private int id;
        private String type, name, address, email, phoneno, position, department, username, password;

        // constructor
        public User() {
            type = "student";
            id = 0;
            name = "";
            address = "";
            email = "";
            phoneno = "";
            position = "";
            department = "";
            username = "";
            password = "";
        }

        public User(int id, String type, String name, String address, String email, String phoneno, String position, String department, String username, String password) {
            this.type = type;
            this.id = id;
            this.name = name;
            this.address = address;
            this.email = email;
            this.phoneno = phoneno;
            this.position = position;
            this.department = department;
            this.username = username;
            this.password = password;
        }

        public String getType() {
            return type;
        }

        // setter and getter
        public void setType(String type) {
            this.type = type;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
    // -------------------------------------------------------------------------------------------------------------
}
