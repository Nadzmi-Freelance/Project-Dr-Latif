package com.example.seladanghijau.projectdrlatif;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

public class PDFViewer extends ActionBarActivity {
    WebView pdfViewer;

    String url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        // get the pdf url
        url = "";

        // show the pdf
        pdfViewer = new WebView(PDFViewer.this);
        pdfViewer.getSettings().setJavaScriptEnabled(true);
        pdfViewer.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
        setContentView(pdfViewer);
    }

}
