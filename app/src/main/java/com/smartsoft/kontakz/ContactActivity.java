package com.smartsoft.kontakz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {
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
                finish();
            }
        });
    }
}
