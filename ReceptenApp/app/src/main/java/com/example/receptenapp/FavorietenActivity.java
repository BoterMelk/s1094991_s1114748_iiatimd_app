package com.example.receptenapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptenapp.database.DataSource;
import com.example.receptenapp.database.model.Favourite;
import com.example.receptenapp.database.model.Recipe;
import com.example.receptenapp.database.model.Recipe_2;

import java.util.ArrayList;

public class FavorietenActivity extends AppCompatActivity implements ReceptAdapter.RecyclerViewClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ImageButton loginButton;
    private ImageButton favorietenButton;
    ArrayList<Recipe> mRecipelist = new ArrayList<>();
    ArrayList<Favourite> mFavouritesList = new ArrayList<>();
    DataSource dataSource;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorieten);

        progressBar = new ProgressDialog(FavorietenActivity.this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading ....");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        mRecipelist = new ArrayList<>();
        mFavouritesList = new ArrayList<>();
        dataSource = new DataSource(FavorietenActivity.this);
        dataSource.open();

        recyclerView = findViewById(R.id.recyclerView);
        mRecipelist = new ArrayList<>();

        loginButton = findViewById(R.id.profileActivityButton2);
        loginButton.setOnClickListener(v -> {
            if(UtitlityClass.getLoginId(FavorietenActivity.this).length() > 0){
                Intent userIntent = new Intent(this, UserActivity.class);
                startActivity(userIntent);
            } else {
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UtitlityClass.getLoginId(FavorietenActivity.this).length() > 0) {
            mRecipelist = new ArrayList<>();
            mRecipelist = new ArrayList<>();
            new AsyncTaskRunner().execute();
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, Integer, String> {


        protected AsyncTaskRunner() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            mFavouritesList = (ArrayList<Favourite>) dataSource.getAllFavProducts(UtitlityClass.getLoginId(FavorietenActivity.this));

            if (mFavouritesList.size() > 0) {
                for (int i = 0; i < mFavouritesList.size(); i++) {
                    Recipe_2 mRecipe_2 = dataSource.getRecipe(mFavouritesList.get(i).getId());
                    if (mRecipe_2 != null) {
                        Recipe mRecipe = new Recipe();


                        mRecipe.setId(mRecipe_2.getId());
                        mRecipe.setImage(mRecipe_2.getImage());
                        mRecipe.setVegetarian(mRecipe_2.getVegetarian());
                        mRecipe.setSummary(mRecipe_2.getSummary());
                        mRecipe.setTitle(mRecipe_2.getTitle());

                        mRecipe.setInstructions(mRecipe_2.getInstructions());
                        mRecipe.setFavoriet(true);

                        mRecipelist.add(mRecipe);
                    }
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
        intent.putExtra("naam", mRecipelist.get(position).getTitle());
        intent.putExtra("beschrijving", mRecipelist.get(position).getSummary());
        intent.putExtra("favoriet", mRecipelist.get(position).isFavoriet());
        intent.putExtra("imagelink", mRecipelist.get(position).getImage());
        startActivity(intent);
    }
}
