package com.kamble.palash.mynotes;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Palash on 03/04/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    // When table is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(Constants.CREATE_TB);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Upgrade TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Constants.TB_NAME);
        onCreate(db);
    }
}