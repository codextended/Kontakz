package com.smartsoft.kontakz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smartsoft.kontakz.controllers.ContactAdapter;
import com.smartsoft.kontakz.db.ContactProvider;
import com.smartsoft.kontakz.model.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "contact.txt";

    public static final int CONTACT_REQUEST_CODE = 10;

    private ArrayList<Contact> contactList = new ArrayList<>();
    private ArrayAdapter<Contact> adapter;
    private ListView contactListView;

    private ContactProvider mProvider;

    private int counter = 0;
    private boolean isRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        mProvider = new ContactProvider(this);

        if (savedInstanceState != null){
            counter = savedInstanceState.getInt("counter");
            isRotate = savedInstanceState.getBoolean("isRotate");
        }






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivityForResult(intent, CONTACT_REQUEST_CODE);
            }
        });



    }

    public void updateUI(){
        contactList = mProvider.getAllContact();
        adapter = new ContactAdapter(this, contactList);
        contactListView = findViewById(R.id.contactListView);
        contactListView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isRotate){
            counter++;
        }


        View parent = findViewById(android.R.id.content);
        Snackbar.make(parent, String.format("Activite afiche %d fois.", counter), Snackbar.LENGTH_LONG).show();

        isRotate = false;

        mProvider.openDatabase();
        updateUI();
    }

    @Override
    protected void onPause() {
        adapter.clear();
        mProvider.closeDatabase();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == CONTACT_REQUEST_CODE){
//
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("contactList", contactList);
        outState.putInt("counter", counter);
        outState.putBoolean("isRotate", !isRotate);

        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
