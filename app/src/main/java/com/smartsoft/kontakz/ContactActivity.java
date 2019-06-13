package com.smartsoft.kontakz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartsoft.kontakz.db.ContactProvider;
import com.smartsoft.kontakz.model.Contact;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ContactActivity extends AppCompatActivity {

    public static final String FILE_NAME = "contact.txt";

    private Button saveButton;
    private EditText prenomET;
    private EditText nomET;
    private EditText phoneET;

    private ContactProvider mProvider;

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
                setResult(RESULT_OK, intent);

                mProvider = new ContactProvider(ContactActivity.this);
                mProvider.openDatabase();

                mProvider.addContact(myContact);


                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        if (mProvider != null) {
            mProvider.closeDatabase();
        }

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
