package com.example.receptenapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recept_info);
        TextView receptNaam = findViewById(R.id.receptInfoNaam);

//        String naam = "Geen";
//
//        Bundle extras = getIntent().getExtras();
//        if(extras != null) {
//            naam = extras.getString("naam");
//        }

        receptNaam.setText("wat");

    }
}
