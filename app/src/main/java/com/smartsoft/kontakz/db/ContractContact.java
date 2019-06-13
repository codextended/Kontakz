package com.smartsoft.kontakz.db;

public class ContractContact {

    public static final String TABLE_NAME = "contact";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PHONE = "phone";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRENOM + ", " + COLUMN_NOM + ", " +
            COLUMN_PHONE + ")";
    public static final String DROP_TABLE = "DROP_TABLE" + TABLE_NAME;

    public static final String[] ALL_COLUMN = {COLUMN_ID, COLUMN_PRENOM, COLUMN_NOM, COLUMN_PHONE};
}
