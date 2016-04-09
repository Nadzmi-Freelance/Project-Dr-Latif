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
    // element dalam activity
    EditText name, email, username, password;
    TextView gotoLogin;
    Button register;
    Spinner userTypeList;
    ProgressDialog pDialog;
    String[] userType;

    // other attributes
    String inUserType, inUsername, inPassword, inName, inEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // --------------- initialize every object ------------------
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
                Intent loginPageStudent = new Intent(RegisterPage.this, LoginPage.class);

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
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

            inUserType = userTypeList.getSelectedItem().toString();
            inUsername = username.getText().toString();
            inPassword = password.getText().toString();
            inName = name.getText().toString();
            inEmail = email.getText().toString();

            if(inUserType.equalsIgnoreCase("Student"))
                inUserType = "student";
            else if(inUserType.equalsIgnoreCase("Staff"))
                inUserType = "staff";
            else if(inUserType.equalsIgnoreCase("Library Staff"))
                inUserType = "stafflibrary";
        }

        protected Integer doInBackground(Void... params) {
            int registerResult = 3;

            if((inUsername!=null) && (inPassword!=null) && (inName!=null) && (inEmail!=null)) {
                try {
                    // -------------------- send http post request to request for book details data --------------------------------
                    HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                    // ------------------------------ setup data for the post request ----------------------------------------------
                    List<NameValuePair> postData = new ArrayList<NameValuePair>();
                    postData.add(new BasicNameValuePair("inUserType", "" + inUserType));
                    postData.add(new BasicNameValuePair("inName", "" + inName));
                    postData.add(new BasicNameValuePair("inEmail", "" + inEmail));
                    postData.add(new BasicNameValuePair("inUsername", "" + inUsername));
                    postData.add(new BasicNameValuePair("inPassword", "" + inPassword));
                    // -------------------------------------------------------------------------------------------------------------

                    // ------------------ retrieve the requested data -------------------------------------------
                    // get the result from http post
                    String data = httpHandler.result("http://seladanghijau.netai.net/php/register.php", postData);

                    if (httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                        // retrieve data from JSON string
                        JSONObject jObj = new JSONObject(data);
                        JSONArray jArray = jObj.getJSONArray("register");

                        // check result for any error
                        JSONObject studentJson = jArray.getJSONObject(0);
                        registerResult = studentJson.getInt("register_result");
                    }
                    // -------------------------------------------------------------------------------------------
                } catch (Exception e) { e.printStackTrace(); }
            } else registerResult = 3;

            return registerResult;
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();

            switch(result) {
                case 0:
                    Toast.makeText(RegisterPage.this, "Your account has been registered. Please login.", Toast.LENGTH_LONG).show();

                    Intent loginPageStudent = new Intent(RegisterPage.this, LoginPage.class);
                    startActivity(loginPageStudent);
                    finish();
                    break;
                case 1:
                    Toast.makeText(RegisterPage.this, "Failed to register. Account already existed", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(RegisterPage.this, "Failed to register. An error occured.", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(RegisterPage.this, "Please fill up all of the fields.", Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(RegisterPage.this, "An error has occured.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    // -----------------------------------------------------------------------------------------------------------------------
}
