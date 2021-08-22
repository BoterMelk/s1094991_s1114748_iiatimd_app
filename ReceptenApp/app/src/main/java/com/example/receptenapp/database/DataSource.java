package com.example.receptenapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.receptenapp.database.model.DBRecipe;
import com.example.receptenapp.database.model.Favourite;
import com.example.receptenapp.database.model.Recipe;
import com.example.receptenapp.database.model.Recipe_2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mHelper;

    public DataSource(Context context) {

        this.mContext = context;
        mHelper = new DBHelper(mContext);
        mDatabase = mHelper.getWritableDatabase();

    }

    public void open() {
        mDatabase = mHelper.getWritableDatabase();
    }

    public void close() {
        mHelper.close();
    }


    public boolean addRecipe(Recipe_2 mRecipe) {
        ContentValues values = mRecipe.toValues();
        return (mDatabase.insert(DBRecipe.TABLE_NAME, null, values) > -1);

    }

    public Recipe_2 getRecipe(int i) {
        List<Favourite> favProducts = new ArrayList<>();

        Cursor cursor = null;
        String parameter[] = {String.valueOf(i)};


        cursor = mDatabase.query(DBRecipe.TABLE_NAME, DBRecipe.ALL_COLUMN,
                DBRecipe.KEY_ID + "=?", parameter, null, null, null);
        Recipe_2 product = null;
        while (cursor.moveToNext()) {
            product = new Recipe_2();
            product.setId(cursor.getInt(cursor.getColumnIndex(DBRecipe.KEY_ID)));
            product.setTitle(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_TITLE)));
            product.setImage(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_IMGURL)));
            product.setSummary(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_SUMMARY)));
            product.setVegetarian(cursor.getInt(cursor.getColumnIndex(DBRecipe.KEY_ISVEGETARIAN)) == 1);
            product.setInstructions(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_INSTRUCTIONS)));
            product.setIngredients(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_INGREDIENTS)));


        }
        cursor.close();
        return product;

    }


    public List<Recipe_2> getAllRecipe() {
        List<Recipe_2> favProducts = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBRecipe.TABLE_NAME, DBRecipe.ALL_COLUMN,
                null, null, null, null, null);

        while (cursor.moveToNext()) {

            Recipe_2 product = new Recipe_2();
            product.setId(cursor.getInt(cursor.getColumnIndex(DBRecipe.KEY_ID)));
            product.setTitle(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_TITLE)));
            product.setImage(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_IMGURL)));
            product.setSummary(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_SUMMARY)));
            product.setVegetarian(cursor.getInt(cursor.getColumnIndex(DBRecipe.KEY_ISVEGETARIAN)) == 1);
            product.setInstructions(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_INSTRUCTIONS)));
            product.setIngredients(cursor.getString(cursor.getColumnIndex(DBRecipe.KEY_INGREDIENTS)));

            /*try {
                product.setDate(UtilityClass.stringToDate(cursor.getString(cursor.getColumnIndex(DBFavouriteProducts.COLUMN_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            favProducts.add(product);

        }

        return favProducts;

    }

    public long getFavRecipe() {
        return DatabaseUtils.queryNumEntries(mDatabase, DBRecipe.TABLE_NAME);
    }

    public boolean addFavProduct(Favourite product) {
        ContentValues values = product.toValues();
        return (mDatabase.insert(DBFavourite.TABLE_NAME, null, values) > -1);

    }

    public long getFavProductCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, DBFavourite.TABLE_NAME);
    }

    public List<Favourite> getAllFavProducts(String i) {
        List<Favourite> favProducts = new ArrayList<>();

        Cursor cursor = null;
        String parameter[] = {String.valueOf(i)};


        cursor = mDatabase.query(DBFavourite.TABLE_NAME, DBFavourite.ALL_COLUMN,
                DBFavourite.KEY_USERID + "=?", parameter, null, null, null);


        while (cursor.moveToNext()) {

            Favourite product = new Favourite();
            product.setId(cursor.getInt(cursor.getColumnIndex(DBFavourite.KEY_ID)));
            product.setFavourite(cursor.getInt(cursor.getColumnIndex(DBFavourite.KEY_IS_FAVOURITE)));
            product.setUserid(cursor.getString(cursor.getColumnIndex(DBFavourite.KEY_USERID)));
            /*try {
                product.setDate(UtilityClass.stringToDate(cursor.getString(cursor.getColumnIndex(DBFavouriteProducts.COLUMN_DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            favProducts.add(product);

        }

        return favProducts;

    }


    public void removeFavProducts() {
        mDatabase.execSQL("delete from " + DBFavourite.TABLE_NAME);
    }

    public void removeFavProducts(int i, String i2) {
        String parameter[] = {String.valueOf(i), String.valueOf(i2)};
        mDatabase.delete(DBFavourite.TABLE_NAME, DBFavourite.KEY_ID + "=? AND " + DBFavourite.KEY_USERID + "=?", parameter);
    }

    public Favourite getFavProductbyId(int i, String i2) {
        List<Favourite> favProducts = new ArrayList<>();

        Cursor cursor = null;
        String parameter[] = {String.valueOf(i), String.valueOf(i2)};

        Favourite product = null;
        cursor = mDatabase.query(DBFavourite.TABLE_NAME, DBFavourite.ALL_COLUMN,
                DBFavourite.KEY_ID + "=? AND " + DBFavourite.KEY_USERID + "=?", parameter, null, null, null);
        while (cursor.moveToNext()) {

            product = new Favourite();
            product.setId(cursor.getInt(cursor.getColumnIndex(DBFavourite.KEY_ID)));
            product.setFavourite(cursor.getInt(cursor.getColumnIndex(DBFavourite.KEY_IS_FAVOURITE)));
            product.setUserid(cursor.getString(cursor.getColumnIndex(DBFavourite.KEY_USERID)));
        }

        return product;

    }


}
