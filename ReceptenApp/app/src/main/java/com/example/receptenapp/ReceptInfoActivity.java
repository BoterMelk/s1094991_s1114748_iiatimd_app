package com.example.receptenapp;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static com.example.receptenapp.R.drawable.heart_white;

public class ReceptInfoActivity extends AppCompatActivity {

    private TextView receptNaam;
    private TextView receptBeschrijving;
    private ImageButton receptInfoBackButton;
    private ToggleButton receptInfoFavorietButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptinfo);

        receptNaam = findViewById(R.id.receptInfoNaam);
        receptBeschrijving = findViewById(R.id.receptInfoBeschrijving);
        receptInfoBackButton = findViewById(R.id.receptInfoBackButton);
        receptInfoFavorietButton = findViewById(R.id.receptenInfoFavorietButton);

        activitySetup();

        receptInfoFavorietButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_black));
            else
                receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_white));
        });

        receptInfoBackButton.setOnClickListener(v -> finish());
    }

    public void activitySetup() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            receptNaam.setText(extras.getString("naam"));
            receptBeschrijving.setText(extras.getString("beschrijving"));
            receptInfoFavorietButton.setChecked(extras.getBoolean("favoriet"));
        }

        if(receptInfoFavorietButton.isChecked() == false) {
            receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_white));
        } else {
            receptInfoFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart_black));
        }
    }
}
