package com.example.companydetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "Company.db";
    public static final String TABLE_NAME = "company_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "COMPANY_NAME";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "FOUNDED_DATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPANY_NAME TEXT, DESCRIPTION TEXT, FOUNDED_DATE TEXT)");
            Log.d(TAG, "Table created successfully.");
        } catch (Exception e) {
            Log.e(TAG, "Error while creating table: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
            Log.d(TAG, "Table upgraded successfully.");
        } catch (Exception e) {
            Log.e(TAG, "Error during table upgrade: " + e.getMessage());
        }
    }

    // Insert company data into SQLite
    public boolean insertData(String companyName, String description, String foundedDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, companyName);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, foundedDate);

        try {
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                Log.e(TAG, "Insertion failed.");
                return false;
            } else {
                Log.d(TAG, "Data inserted successfully.");
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error during data insertion: " + e.getMessage());
            return false;
        }
    }

    // Retrieve all data from SQLite
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            Log.d(TAG, "Data retrieval successful.");
        } catch (Exception e) {
            Log.e(TAG, "Error during data retrieval: " + e.getMessage());
        }
        return cursor;
    }
}
