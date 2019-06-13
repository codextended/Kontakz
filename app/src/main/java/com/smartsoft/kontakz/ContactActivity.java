package com.smartsoft.kontakz;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
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

                saveContact(myContact);
                Toast.makeText(ContactActivity.this, "Save to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    private void saveContact(Contact myContact) {

        String stringContact = String.format("%s %s %s", myContact.getPrenom(), myContact.getNom(), myContact.getPhone());



        FileOutputStream fOut = null;
        OutputStreamWriter myOutWriter = null;

        try {
            fOut = openFileOutput(FILE_NAME, MODE_APPEND);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(stringContact);
            myOutWriter.append("\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fOut != null) {
                try {
                    myOutWriter.close();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
