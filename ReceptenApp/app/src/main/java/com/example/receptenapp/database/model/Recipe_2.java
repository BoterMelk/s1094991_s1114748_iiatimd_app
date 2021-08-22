package com.example.receptenapp.database.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe_2   {

    private String title;

    private String image;

    private Boolean vegetarian;


    private Integer id;


    private String instructions;
    private String ingredients;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    private boolean favoriet;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @SerializedName("summary")
    @Expose
    private String summary;



    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isFavoriet() {
        return favoriet;
    }

    public void setFavoriet(boolean favoriet) {
        this.favoriet = favoriet;
    }



    public ContentValues toValues() {

        ContentValues values = new ContentValues(4);
        //values.put(DBRecipe.COLUMN_ID, getCart_id());
        values.put(DBRecipe.KEY_ID, getId());
        values.put(DBRecipe.KEY_TITLE, getTitle());
        values.put(DBRecipe.KEY_SUMMARY, getSummary());
        values.put(DBRecipe.KEY_IMGURL, getImage());
        values.put(DBRecipe.KEY_ISVEGETARIAN, (byte) (vegetarian == null ? 0 : vegetarian ? 1 : 2));
        values.put(DBRecipe.KEY_INGREDIENTS, "");
        values.put(DBRecipe.KEY_INSTRUCTIONS, getInstructions());

        return values;
    }
}
