package com.example.seladanghijau.projectdrlatif;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

public class Analysis extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    // items in activity
    ProgressDialog pDialog;
    TextView txtTotalUser, txtTotalStudent, txtTotalStaff, txtTotalStaffLibrary;
    TextView txtTotalBook, txtTotalNewspaper;
    ExpandableListView explvTotalBookByAuthor, explvTotalBookByPublisher, explvTotalBookByYearPublished;

    // drawer variables
    ActionBarDrawerToggle drawerListener;
    DrawerLayout drawerLayout;
    ListView menuList;
    String[] menus;

    // other variables

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        // -------------------------------- initialize items in activity ------------------------------
        txtTotalUser = (TextView) findViewById(R.id.txtTotalUser);
        txtTotalStudent = (TextView) findViewById(R.id.txtTotalStudent);
        txtTotalStaff = (TextView) findViewById(R.id.txtTotalStaff);
        txtTotalStaffLibrary = (TextView) findViewById(R.id.txtTotalStaffLibrary);
        txtTotalBook = (TextView) findViewById(R.id.txtTotalBook);
        txtTotalNewspaper = (TextView) findViewById(R.id.txtTotalNewspaper);
        explvTotalBookByAuthor = (ExpandableListView) findViewById(R.id.explvTotalBookByAuthor);
        explvTotalBookByPublisher = (ExpandableListView) findViewById(R.id.explvTotalBookByPublisher);
        explvTotalBookByYearPublished = (ExpandableListView) findViewById(R.id.explvTotalBookByYearPublished);
        // --------------------------------------------------------------------------------------------

        // initialize variables

        // -------------- drawer actions --------------------
        menus = getResources().getStringArray(R.array.menuAdmin);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(drawerListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // list utk drawer
        menuList.setAdapter(new ArrayAdapter<>(this, R.layout.menulist_layout, menus));
        menuList.setOnItemClickListener(this);
        // -------------------------------------------------
    }

    // ------------------------------------ OnClickListener --------------------------------------------------
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
        }
    }
    // -------------------------------------------------------------------------------------------------------
}
