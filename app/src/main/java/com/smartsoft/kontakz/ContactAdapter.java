package com.smartsoft.kontakz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactAdapter extends ArrayAdapter {
    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Contact contact = (Contact) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);

        String fullName = String.format("%s, %s", contact.getNom().toUpperCase(), contact.getPrenom());

        tvName.setText(fullName);
        tvPhone.setText(contact.getPhone());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("contact", contact);
                getContext().startActivity(intent);
            }
        });


        return convertView;
    }
}
