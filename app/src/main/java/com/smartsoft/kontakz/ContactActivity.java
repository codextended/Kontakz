package com.smartsoft.kontakz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartsoft.kontakz.db.ContactProvider;
import com.smartsoft.kontakz.model.Contact;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ContactActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myref;


    private Button saveButton;
    private EditText prenomET;
    private EditText nomET;
    private EditText phoneET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        prenomET = findViewById(R.id.prenomEditText);
        nomET = findViewById(R.id.nomEditText);
        phoneET = findViewById(R.id.phoneEditText);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact myContact = new Contact(prenomET.getText().toString(), nomET.getText().toString(), phoneET.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("contact", myContact);


                database = FirebaseDatabase.getInstance();
                myref = database.getReference().child("contact");

                myref.push().setValue(myContact);


                finish();
            }
        });
    }




}
