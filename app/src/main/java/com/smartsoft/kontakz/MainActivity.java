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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int CONTACT_REQUEST_CODE = 10;

    private ArrayList<Contact> contactList;
    private ArrayAdapter<Contact> adapter;
    private ListView contactListView;

    private int counter = 0;
    private boolean isRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactListView = findViewById(R.id.contactListView);


        contactList = new ArrayList<>();

        if (savedInstanceState != null){
            contactList = (ArrayList<Contact>) savedInstanceState.getSerializable("contactList");
            counter = savedInstanceState.getInt("counter");
            isRotate = savedInstanceState.getBoolean("isRotate");
        }else {
            contactList.add(new Contact("Smath", "Cadet", "4587-9876"));
            contactList.add(new Contact("Fritz", "Jeune", "4897-3452"));
            contactList.add(new Contact("Macener", "Assad", "48543-0934"));
        }



        adapter = new ContactAdapter(this, contactList);

        contactListView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivityForResult(intent, CONTACT_REQUEST_CODE);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isRotate){
            counter++;
        }

//        Toast.makeText(this, String.format("Activite afiche %d fois.", counter), Toast.LENGTH_SHORT).show();

        View parent = findViewById(android.R.id.content);
        Snackbar.make(parent, String.format("Activite afiche %d fois.", counter), Snackbar.LENGTH_LONG).show();

        isRotate = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == CONTACT_REQUEST_CODE){
            Contact myContact = (Contact) data.getExtras().getSerializable("contact");
            contactList.add(myContact);
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
