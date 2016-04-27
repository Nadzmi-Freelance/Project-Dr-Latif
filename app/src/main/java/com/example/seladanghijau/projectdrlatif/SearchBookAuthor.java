package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class SearchBookAuthor extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    // elements in activity
    ProgressDialog pDialog;
    EditText txtSearchBook;
    ListView lvBookList;
    Button btnSearchBook;

    // variables
    BaseAdapter listTajukBukuAdapter;
    List<String> listTajukBuku;
    int[] bookId;
    String inBookAuthor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book_author);

        // initialize elements in activity
        txtSearchBook = (EditText) findViewById(R.id.txtSearchBook);
        btnSearchBook = (Button) findViewById(R.id.btnSearchBook);
        lvBookList = (ListView) findViewById(R.id.lvBookList);

        // set OnClickListener
        btnSearchBook.setOnClickListener(this);
        lvBookList.setOnItemClickListener(SearchBookAuthor.this); // set onclicklistener utk setiap item dlm ListView

        // set adapter
        listTajukBuku = new ArrayList<>();
    }

    // ----------------------------------------- OnCLickListener ----------------------------------------------
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearchBook:
                new GetBook().execute();
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: // destroy this activity if user clicked back button
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
    // --------------------------------------------------------------------------------------------------------

    // -------------------------------------- AsyncTask class ----------------------------------------------
    private class GetBook extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(SearchBookAuthor.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            inBookAuthor = txtSearchBook.getText().toString();
            listTajukBuku.clear();

            try {
                HTTPHandler httpHandler = new HTTPHandler();

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                postData.add(new BasicNameValuePair("inSearchType", "author"));
                postData.add(new BasicNameValuePair("inBookAuthor", "" + inBookAuthor));
                // -------------------------------------------------------------------------------------------------------------

                String responseData = httpHandler.result("http://uitmkedah.net/nadzmi/php/RetrieveBook.php", postData);

                if(httpHandler.getStatus() == HttpURLConnection.HTTP_OK) { // http request "OK": successfully connect to database
                    JSONObject jObj = new JSONObject(responseData);
                    JSONArray jArray = jObj.getJSONArray("books");

                    bookId = new int[jArray.length()]; // set the size according to the size of the json array
                    for(int y=0 ; y<jArray.length() ; y++) { // retrieve suma data dari json
                        JSONObject tempJSON = jArray.getJSONObject(y);

                        // get data from JSON(elementarily)
                        String book_title = tempJSON.getString("book_title");
                        bookId[y] = tempJSON.getInt("book_id");

                        listTajukBuku.add(book_title); // retrieve tajuk buku masuk kedalam list buku
                    }
                } return false;
            } catch (Exception e) { e.printStackTrace(); }

            return true; // return true because successfully carry on the operation
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            listTajukBukuAdapter = new ArrayAdapter<>(SearchBookAuthor.this, R.layout.menulist_layout, listTajukBuku);
            lvBookList.setAdapter(listTajukBukuAdapter);

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
        }
    }
    // -----------------------------------------------------------------------------------------------------

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_book_author, menu);
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
}
