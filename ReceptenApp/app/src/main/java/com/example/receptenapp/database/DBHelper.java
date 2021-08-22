package com.example.receptenapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.receptenapp.database.model.DBRecipe;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "recipebook.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBFavourite.SQL_CREATE);
        db.execSQL(DBRecipe.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DBFavourite.SQL_DELETE);
        db.execSQL(DBRecipe.SQL_DELETE);


        onCreate(db);
    }


}
