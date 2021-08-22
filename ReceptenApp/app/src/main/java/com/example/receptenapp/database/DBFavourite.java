package com.example.receptenapp.database;

public class DBFavourite {

    public static final String TABLE_NAME = "favourites";

    public static final String COLUMN_ID = "fav_id";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_FAVOURITE = "IsFavourite";
    public static final String KEY_USERID = "userId";

    public static final String[] ALL_COLUMN =
            {COLUMN_ID, KEY_ID, KEY_IS_FAVOURITE, KEY_USERID};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    KEY_ID + " INTEGER, " +
                    KEY_IS_FAVOURITE + " INTEGER," +
                    KEY_USERID + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
