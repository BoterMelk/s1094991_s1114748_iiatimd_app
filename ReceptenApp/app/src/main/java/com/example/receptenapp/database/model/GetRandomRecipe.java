package com.example.receptenapp.database.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRandomRecipe implements Parcelable
{

@SerializedName("recipes")
@Expose
private List<Recipe> recipes = null;
public final static Creator<GetRandomRecipe> CREATOR = new Creator<GetRandomRecipe>() {


@SuppressWarnings({
"unchecked"
})
public GetRandomRecipe createFromParcel(android.os.Parcel in) {
return new GetRandomRecipe(in);
}

public GetRandomRecipe[] newArray(int size) {
return (new GetRandomRecipe[size]);
}

}
;

protected GetRandomRecipe(android.os.Parcel in) {
in.readList(this.recipes, ( Recipe.class.getClassLoader()));
}

public GetRandomRecipe() {
}

public List<Recipe> getRecipes() {
return recipes;
}

public void setRecipes(List<Recipe> recipes) {
this.recipes = recipes;
}

public void writeToParcel(android.os.Parcel dest, int flags) {
dest.writeList(recipes);
}

public int describeContents() {
return 0;
}

}