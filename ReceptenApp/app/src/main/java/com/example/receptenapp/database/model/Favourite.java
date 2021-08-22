package com.example.receptenapp.database.model;

import android.content.ContentValues;

import com.example.receptenapp.database.DBFavourite;

public class Favourite {

    private int id;
    private int favourite;
    private String userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ContentValues toValues(){

        ContentValues values = new ContentValues(4);
        //values.put(DBCartProducts.COLUMN_ID, getCart_id());
        values.put(DBFavourite.KEY_ID, getId());
        values.put(DBFavourite.KEY_IS_FAVOURITE, getFavourite());
        values.put(DBFavourite.KEY_USERID, getUserid());


        return values;
    }
}
