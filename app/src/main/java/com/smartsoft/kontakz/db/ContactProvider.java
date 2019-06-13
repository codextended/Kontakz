package com.smartsoft.kontakz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smartsoft.kontakz.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactProvider {

    private Context mContext;
    private SQLiteOpenHelper mOpenHelper;
    private SQLiteDatabase mDataBase;

    public ContactProvider(Context mContext) {
        this.mContext = mContext;
        this.mOpenHelper = new DBHelper(mContext);
        this.mDataBase = mOpenHelper.getWritableDatabase();
    }

    public void openDatabase(){
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mOpenHelper.close();
    }

    public void addContact(Contact contact){
        ContentValues values = contact.toValues();
        mDataBase.insert(ContractContact.TABLE_NAME, null, values);
    }

    public Contact getContact(String prenom){
        Contact contact = new Contact();

        Cursor cursor = mDataBase.query(ContractContact.TABLE_NAME, ContractContact.ALL_COLUMN, ContractContact.COLUMN_PRENOM + " = ?", new String[]{prenom}, null, null, null);

        while (cursor.moveToNext()){
            contact.setPrenom(cursor.getString(cursor.getColumnIndex(ContractContact.COLUMN_PRENOM)));
            contact.setNom(cursor.getString(cursor.getColumnIndex(ContractContact.COLUMN_NOM)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContractContact.COLUMN_PHONE)));
        }
        cursor.close();

        return contact;
    }

    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contacts = new ArrayList<>();

        Cursor cursor = mDataBase.query(ContractContact.TABLE_NAME, ContractContact.ALL_COLUMN, null, null, null, null, null);

        while (cursor.moveToNext()){
            Contact contact = new Contact();

            contact.setPrenom(cursor.getString(cursor.getColumnIndex(ContractContact.COLUMN_PRENOM)));
            contact.setNom(cursor.getString(cursor.getColumnIndex(ContractContact.COLUMN_NOM)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContractContact.COLUMN_PHONE)));

            contacts.add(contact);
        }
        cursor.close();

        return contacts;

    }
}
