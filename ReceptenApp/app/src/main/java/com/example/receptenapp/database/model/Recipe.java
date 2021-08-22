package com.example.receptenapp.database.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.receptenapp.database.DBFavourite;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe implements Parcelable {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("vegetarian")
    @Expose
    private Boolean vegetarian;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("instructions")
    @Expose
    private String instructions;

    private boolean favoriet;

    public Recipe() {

    }


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

    public Recipe(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        byte tmpVegetarian = in.readByte();
        vegetarian = tmpVegetarian == 0 ? null : tmpVegetarian == 1;
        summary = in.readString();
        instructions = in.readString();
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeByte((byte) (vegetarian == null ? 0 : vegetarian ? 1 : 2));
        dest.writeString(summary);
        dest.writeString(instructions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };


}
