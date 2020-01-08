package com.pedro.telephonebook.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contacts_data_base";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "contacts_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AVATAR = "avatar";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NICKNAME = "nickname";
    public static final String COLUMN_TEL = "tel";
    public static final String COLUMN_EMAIL = "email";

    private static final String SQL_ENTRIES = "CREATE TABLE " +
            TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_AVATAR + " TEXT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_NICKNAME + " TEXT, " +
            COLUMN_TEL + " TEXT, " +
            COLUMN_EMAIL + " TEXT)";

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
