package com.example.receptenapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.receptenapp.database.DataSource;
import com.example.receptenapp.database.model.Favourite;

import static com.example.receptenapp.R.drawable.heart_white;

public class ReceptInfoActivity extends AppCompatActivity {

    private TextView receptNaam;
    private TextView receptBeschrijving;
    private ImageView receptInfoBackButton, picture;
    private ToggleButton receptInfoFavorietButton;
    private ImageButton loginButton;
    CircularProgressDrawable circularProgressDrawable;
    DataSource dataSource;
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptinfo);

        dataSource = new DataSource(ReceptInfoActivity.this);
        receptNaam = findViewById(R.id.receptNaam);
        receptBeschrijving = findViewById(R.id.receptBeschrijving);
        receptInfoBackButton = findViewById(R.id.receptInfoBackButton);
        receptInfoFavorietButton = findViewById(R.id.receptenInfoFavorietButton);
        picture = findViewById(R.id.picture);
        circularProgressDrawable = new CircularProgressDrawable(ReceptInfoActivity.this);
        circularProgressDrawable.setStrokeWidth(5);
        circularProgressDrawable.setCenterRadius(65);
        circularProgressDrawable.start();
        activitySetup();

        loginButton = findViewById(R.id.profileActivityButton);
        loginButton.setOnClickListener(v -> {
            if(UtitlityClass.getLoginId(ReceptInfoActivity.this).length() > 0){
                Intent userIntent = new Intent(this, UserActivity.class);
                startActivity(userIntent);
            } else {
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


        receptInfoFavorietButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_black));
                Favourite mFavourite = new Favourite();
                mFavourite.setId(ids);
                mFavourite.setFavourite(1);
                mFavourite.setUserid(UtitlityClass.getLoginId(ReceptInfoActivity.this));
                dataSource.addFavProduct(mFavourite);

//                recepten[position].setFavoriet(true);

//                krijg op een of andere manier de huidige positie in de array
//                geen idee hoe though
//                hmmmmm
//                ik ga morgen verder hiermee
            } else {
                receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_white));
//                recepten[position].setFavoriet(false);
                dataSource.removeFavProducts(ids, UtitlityClass.getLoginId(ReceptInfoActivity.this));
            }
        });

        receptInfoBackButton.setOnClickListener(v -> finish());
    }

    public void activitySetup() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ids = extras.getInt("idd");
            receptNaam.setText(extras.getString("naam"));

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                receptBeschrijving.setText(Html.fromHtml(extras.getString("beschrijving"), Html.FROM_HTML_MODE_LEGACY));

            } else {
                receptBeschrijving.setText(Html.fromHtml("<html><body>" + extras.getString("beschrijving") + "</body></html>"));
            }
//            receptBeschrijving.setText(extras.getString("beschrijving"));
            receptInfoFavorietButton.setChecked(extras.getBoolean("favoriet"));
        }

        if (receptInfoFavorietButton.isChecked() == false) {
            receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_white));
        } else {
            receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_black));
        }

        Glide.with(ReceptInfoActivity.this).load(extras.getString("imagelink")).apply(new RequestOptions().placeholder(circularProgressDrawable).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).into(picture);

    }
}
