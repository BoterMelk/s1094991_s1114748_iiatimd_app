package com.example.receptenapp.database.model;

public class DBRecipe {

    public static final String TABLE_NAME = "recipes";

    public static final String COLUMN_ID = "rec_id";


    public static final String KEY_ID = "id";
    public static final String KEY_IMGURL = "imgURL";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ISVEGETARIAN = "vegetarian";
    public static final String KEY_SUMMARY = "summary";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_INSTRUCTIONS = "instructions";

    public static final String[] ALL_COLUMN =
            {COLUMN_ID, KEY_ID, KEY_IMGURL,KEY_TITLE, KEY_ISVEGETARIAN, KEY_SUMMARY, KEY_INGREDIENTS, KEY_INSTRUCTIONS};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    KEY_ID + " INTEGER, " +
                    KEY_TITLE + " TEXT," +
                    KEY_IMGURL + " TEXT," +
                    KEY_ISVEGETARIAN + " INTEGER," +
                    KEY_SUMMARY + " TEXT," +
                    KEY_INGREDIENTS + " TEXT," +
                    KEY_INSTRUCTIONS + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
