package com.example.receptenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.receptenapp.database.DataSource;
import com.example.receptenapp.database.model.Favourite;
import com.example.receptenapp.database.model.GetAppData;
import com.example.receptenapp.database.model.GetRandomRecipe;
import com.example.receptenapp.database.model.Recipe;
import com.example.receptenapp.database.model.Recipe_2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements ReceptAdapter.RecyclerViewClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ImageButton loginButton;
    private ImageButton favorietenButton;
    ArrayList<Recipe> mRecipelist = new ArrayList<>();
    DataSource dataSource;
    ProgressDialog progressBar;

    private String email;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading ....");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        email = UtitlityClass.getLoginId(MainActivity.this);
        token = UtitlityClass.getToken(MainActivity.this);

//        UtitlityClass.setLoginId(MainActivity.this, "email");
        dataSource = new DataSource(MainActivity.this);
        dataSource.open();

        recyclerView = findViewById(R.id.recyclerView);
        mRecipelist = new ArrayList<>();
//        setReceptArray();


        loginButton = findViewById(R.id.profileActivityButton);
        loginButton.setOnClickListener(v -> {
            if(UtitlityClass.getLoginId(MainActivity.this).length() > 0){
                Intent userIntent = new Intent(this, UserActivity.class);
                startActivity(userIntent);
            } else {
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        favorietenButton = findViewById(R.id.favorietenActivityButton);
        favorietenButton.setOnClickListener(v -> {
            if (UtitlityClass.getLoginId(MainActivity.this).length() > 0) {
                Intent favIntent = new Intent(this, FavorietenActivity.class);
                startActivity(favIntent);
            }else{
                Intent favIntent = new Intent(this, LoginActivity_empty.class);
                startActivity(favIntent);
            }
        });

        Log.d("Start", "Let's start now!!!!!!!!!");

        if (UtitlityClass.isNetworkAvailable(MainActivity.this)) {
            online_work();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!UtitlityClass.isNetworkAvailable(MainActivity.this)) {
            offline_work();
        }
    }

    private void offline_work() {
        new AsyncTaskRunner_offline().execute();
    }

    private void online_work() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8000/api/recipes/";

        // Request a string response from the provided URL.

//        searchbar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
////                    https://api.spoonacular.com/recipes/complexSearch?query=pasta&maxFat=25&number=2
//                    String url_search = "https://api.spoonacular.com/recipes/complexSearch?query="+s.toString()+"&maxFat=25&number=2&apiKey=799628c184dd4918b97bb1c76736e21f";
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url_search, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Log.e("Start", "Response is: " + response);
//
//                            Type listType = new TypeToken<GetRandomRecipe>() {
//                            }.getType();
//                            GetRandomRecipe mGetRandomRecipe = new Gson().fromJson(response, listType);
//
//                            mRecipelist = (ArrayList<Recipe>) mGetRandomRecipe.getRecipes();
//
//
//                            new AsyncTaskRunner().execute();
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("Start", "That didn't work!");
//                            Log.d("Start", error.toString());
//                        }
//                    });
//
//                    Log.d("Start", "Before adding to queue");
//                    queue.add(stringRequest);
//                    Log.d("Start", "After adding to queue");
//
//
//                } else {
//                    Log.d("s", s.length() + "");
//                    progressBar.dismiss();
//                    mRecipelist = new ArrayList<>();
//                    setAdapter();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.length() == 0) {
//                    mRecipelist = new ArrayList<>();
//                    setAdapter();
//                }
//            }
//        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Start", "Response is: " + response);

                Type listType = new TypeToken<GetRandomRecipe>() {
                }.getType();
                GetRandomRecipe mGetRandomRecipe = new Gson().fromJson(response, listType);

                mRecipelist = (ArrayList<Recipe>) mGetRandomRecipe.getRecipes();


                new AsyncTaskRunner().execute();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Start", "That didn't work!");
                Log.d("Start", error.toString());
            }
        });

        Log.d("Start", "Before adding to queue");
        queue.add(stringRequest);
        Log.d("Start", "After adding to queue");
    }

    private class AsyncTaskRunner extends AsyncTask<String, Integer, String> {
        String result;

        protected AsyncTaskRunner() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            for (int i = 0; i < mRecipelist.size(); i++) {
                Recipe_2 mRecipe_2 = dataSource.getRecipe(mRecipelist.get(i).getId());
                if (mRecipe_2 == null) {
                    Recipe_2 mRecipe = new Recipe_2();
                    mRecipe.setId(mRecipelist.get(i).getId());
                    mRecipe.setImage(mRecipelist.get(i).getImage());
                    mRecipe.setVegetarian(mRecipelist.get(i).getVegetarian());
                    mRecipe.setSummary(mRecipelist.get(i).getSummary());
                    mRecipe.setTitle(mRecipelist.get(i).getTitle());
                    mRecipe.setIngredients("");
                    mRecipe.setInstructions(mRecipelist.get(i).getInstructions());
                    mRecipe.setFavoriet(false);

//                    Favourite mFavourite = dataSource.getFavProductbyId(mRecipelist.get(i).getId(), 1);
//                    if (mFavourite != null) {
//                        mRecipe.setFavoriet(true);
//                    } else {
//                        mRecipe.setFavoriet(false);
//                    }


                    if (dataSource.addRecipe(mRecipe)) {
                        Log.e("success", "success");
                    } else {
                        Log.e("sorry", "sorry");
                    }
                }

                Favourite mFavourite = dataSource.getFavProductbyId(mRecipelist.get(i).getId(), UtitlityClass.getLoginId(MainActivity.this));
                if (mFavourite != null) {
                    mRecipelist.get(i).setFavoriet(true);
                } else {
                    mRecipelist.get(i).setFavoriet(false);
                }
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();
            setAdapter();
        }
    }

    private class AsyncTaskRunner_search extends AsyncTask<String, Integer, String> {
        String result;

        protected AsyncTaskRunner_search() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            for (int i = 0; i < mRecipelist.size(); i++) {
                Recipe_2 mRecipe_2 = dataSource.getRecipe(mRecipelist.get(i).getId());
                if (mRecipe_2 == null) {
                    Recipe_2 mRecipe = new Recipe_2();
                    mRecipe.setId(mRecipelist.get(i).getId());
                    mRecipe.setImage(mRecipelist.get(i).getImage());
                    mRecipe.setVegetarian(mRecipelist.get(i).getVegetarian());
                    mRecipe.setSummary(mRecipelist.get(i).getSummary());
                    mRecipe.setTitle(mRecipelist.get(i).getTitle());
                    mRecipe.setIngredients("");
                    mRecipe.setInstructions(mRecipelist.get(i).getInstructions());
                    mRecipe.setFavoriet(false);

//                    Favourite mFavourite = dataSource.getFavProductbyId(mRecipelist.get(i).getId(), 1);
//                    if (mFavourite != null) {
//                        mRecipe.setFavoriet(true);
//                    } else {
//                        mRecipe.setFavoriet(false);
//                    }


                    if (dataSource.addRecipe(mRecipe)) {
                        Log.e("success", "success");
                    } else {
                        Log.e("sorry", "sorry");
                    }
                }

                Favourite mFavourite = dataSource.getFavProductbyId(mRecipelist.get(i).getId(), UtitlityClass.getLoginId(MainActivity.this));
                if (mFavourite != null) {
                    mRecipelist.get(i).setFavoriet(true);
                } else {
                    mRecipelist.get(i).setFavoriet(false);
                }
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();
            setAdapter();
        }
    }

    private class AsyncTaskRunner_offline extends AsyncTask<String, Integer, String> {
        String result;

        protected AsyncTaskRunner_offline() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<Recipe_2> mRecipe2ArrayList = new ArrayList<>();
            mRecipe2ArrayList = (ArrayList<Recipe_2>) (dataSource.getAllRecipe());
            for (int i = 0; i < mRecipe2ArrayList.size(); i++) {


                Recipe mRecipe = new Recipe();


                mRecipe.setId(mRecipe2ArrayList.get(i).getId());
                mRecipe.setImage(mRecipe2ArrayList.get(i).getImage());
                mRecipe.setVegetarian(mRecipe2ArrayList.get(i).getVegetarian());
                mRecipe.setSummary(mRecipe2ArrayList.get(i).getSummary());
                mRecipe.setTitle(mRecipe2ArrayList.get(i).getTitle());

                mRecipe.setInstructions(mRecipe2ArrayList.get(i).getInstructions());
                mRecipe.setFavoriet(false);

                mRecipelist.add(mRecipe);
            }

            for (int i = 0; i < mRecipelist.size(); i++) {
                Favourite mFavourite = dataSource.getFavProductbyId(mRecipelist.get(i).getId(), UtitlityClass.getLoginId(MainActivity.this));
                if (mFavourite != null) {
                    mRecipelist.get(i).setFavoriet(true);
                } else {
                    mRecipelist.get(i).setFavoriet(false);
                }
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();
            setAdapter();
        }
    }

    private void setAdapter() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerViewAdapter = new ReceptAdapter(mRecipelist, this, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, ReceptInfoActivity.class);
        intent.putExtra("idd", mRecipelist.get(position).getId());
        intent.putExtra("naam", mRecipelist.get(position).getTitle());
        intent.putExtra("beschrijving", mRecipelist.get(position).getSummary());
        intent.putExtra("favoriet", mRecipelist.get(position).isFavoriet());
        intent.putExtra("imagelink", mRecipelist.get(position).getImage());
        startActivity(intent);
    }
}


