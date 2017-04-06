package com.kamble.palash.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.R.attr.name;

/**
 * Created by Palash on 03/04/17.
 */

public class DBAdapter {

    Context c;
    SQLiteDatabase db;
    DBHelper dbHelper;

    public DBAdapter(Context ctx) {
        this.c = ctx;
        dbHelper = new DBHelper(c);
    }

    //open DB
    public DBAdapter openDB() {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    //close
    public void close() {
        try {
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Insert data to database
    public long add(String title, String note) {

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.TITLE, title);
            contentValues.put(Constants.NOTE, note);
            return db.insert(Constants.TB_NAME, Constants.ROW_ID, contentValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Retrieve all players
    public Cursor getAllPlayers() {
        String[] columns = {Constants.ROW_ID, Constants.TITLE, Constants.NOTE};
        return db.query(Constants.TB_NAME, columns, null, null, null, null, null);
    }

    // Update
    public long UPDATE(int id, String title, String note) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.TITLE, title);
            contentValues.put(Constants.NOTE, note);
            // New 2 april
//            contentValues.put(Constants.CURRENT_TIMESTAMP,pos);

            return db.update(Constants.TB_NAME, contentValues, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Delete
    public long Delete(int id) {
        try {
            return db.delete(Constants.TB_NAME, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
