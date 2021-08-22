package com.example.receptenapp.database.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAppData implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("vegetarian")
    @Expose
    private Boolean vegetarian;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    public final static Creator<GetAppData> CREATOR = new Creator<GetAppData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetAppData createFromParcel(android.os.Parcel in) {
            return new GetAppData(in);
        }

        public GetAppData[] newArray(int size) {
            return (new GetAppData[size]);
        }

    };

    protected GetAppData(android.os.Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.vegetarian = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.ingredients, (Ingredient.class.getClassLoader()));
        this.instructions = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetAppData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(image);
        dest.writeValue(vegetarian);
        dest.writeValue(summary);
        dest.writeList(ingredients);
        dest.writeValue(instructions);
    }

    public int describeContents() {
        return 0;
    }

}