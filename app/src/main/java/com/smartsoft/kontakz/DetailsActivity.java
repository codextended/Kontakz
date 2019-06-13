package com.smartsoft.kontakz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView prenomTV;
    TextView nomTV;
    TextView phoneTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        prenomTV = findViewById(R.id.prenomTV);
        nomTV = findViewById(R.id.nomTV);
        phoneTV = findViewById(R.id.phoneTV);

        Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        prenomTV.setText(contact.getPrenom());
        nomTV.setText(contact.getNom().toUpperCase());
        phoneTV.setText(contact.getPhone());
    }
}
