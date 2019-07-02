package com.smartsoft.kontakz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    FirebaseDatabase database;
    DatabaseReference myref;

    private ArrayList<Contact> contactList;
    private ArrayAdapter<Contact> adapter;
    private ListView contactListView;

    private int counter = 0;
    private boolean isRotate = false;
    private static final String TAG = "com.smartsoft.kontakz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myref = database.getReference().child("contact");


        if (savedInstanceState != null){
            counter = savedInstanceState.getInt("counter");
            isRotate = savedInstanceState.getBoolean("isRotate");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

    }

    public void updateUI(){
        contactList  = new ArrayList<>();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot contactSnapshot: dataSnapshot.getChildren()){
                    Contact contact = contactSnapshot.getValue(Contact.class);
                    contactList.add(contact);
                }
                adapter = new ContactAdapter(MainActivity.this, contactList);
                contactListView = findViewById(R.id.contactListView);
                contactListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

        updateUI();
    }

    @Override
    protected void onPause() {
        adapter.clear();
        super.onPause();
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
