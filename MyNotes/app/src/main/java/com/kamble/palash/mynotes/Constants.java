package com.kamble.palash.mynotes;

/**
 * Created by Palash on 03/04/17.
 */

public class Constants {

    // Columns
    static final String ROW_ID = "id";
    static final String TITLE = "title";
    static final String NOTE = "note";
    // static final String CURRENT_TIMESTAMP = "time";

    // DB properties
    static final String DB_NAME = "noteDB";
    static final String TB_NAME = "noteTB";
    static final int DB_VERSION = '1';

    // Create table
    static final String CREATE_TB = "CREATE TABLE noteTB (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT NOT NULL,note TEXT NOT NULL);";
}
