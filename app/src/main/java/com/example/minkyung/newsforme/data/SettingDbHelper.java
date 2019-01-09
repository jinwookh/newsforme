package com.example.minkyung.newsforme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SettingDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = SettingDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "news.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;


    public SettingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_SETTING_TABLE =  "CREATE TABLE " + SettingContract.SettingEntry.TABLE_NAME + " ("
                + SettingContract.SettingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SettingContract.SettingEntry.COLUMN_URI+ " TEXT NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_SETTING_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

}
