package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class PDFViewer extends ActionBarActivity {
    ProgressDialog pDialog;
    WebView webview;

    private int pdf_id;
    private String pdfLink;

    String data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        pdf_id = getIntent().getIntExtra("pdf_id", pdf_id);
        new GetPDF().execute();
    }

    private class GetPDF extends AsyncTask<Void, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();

            // show progress dialog
            pDialog = new ProgressDialog(PDFViewer.this);
            pDialog.setMessage("Tunggu sebentar...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Boolean doInBackground(Void... params) {
            try {
                // -------------------- send http post request to request for book details data --------------------------------
                HTTPHandler httpHandler = new HTTPHandler(); // setup HttpHandler object

                // ------------------------------ setup data for the post request ----------------------------------------------
                List<NameValuePair> postData = new ArrayList<NameValuePair>();
                postData.add(new BasicNameValuePair("inPDFId", "" + pdf_id));
                // -------------------------------------------------------------------------------------------------------------

                // ------------------ retrieve the requested data -------------------------------------------
                // get the result from http post
                data = httpHandler.result("http://uitmkedah.net/nadzmi/php/PDFurl.php", postData);

                if (httpHandler.getStatus() == HttpURLConnection.HTTP_OK) {
                    // retrieve data from JSON string
                    JSONObject jObj = new JSONObject(data);
                    JSONArray jArray = jObj.getJSONArray("pdf");

                    JSONObject jsonObjData = jArray.getJSONObject(0);
                    if(jsonObjData.getString("message").toString().equalsIgnoreCase("success")) {
                        pdfLink = jsonObjData.getString("pdf_url");

                        return true;
                    } else return false;
                }
                // -------------------------------------------------------------------------------------------
            } catch(Exception e) { e.printStackTrace(); }

            return false;
        }

        protected void onPostExecute(Boolean status) {
            super.onPostExecute(status);

            webview = (WebView) findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);

            if(status)
                webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdfLink);

            // dismiss progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();
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
}
