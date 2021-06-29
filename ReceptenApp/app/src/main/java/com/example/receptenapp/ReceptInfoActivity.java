package com.example.receptenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceptInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptinfo);
        TextView receptNaam = findViewById(R.id.receptInfoNaam);
        TextView receptBeschrijving = findViewById(R.id.receptInfoBeschrijving);

        ImageButton receptenInfoBackButton = findViewById(R.id.receptenInfoBackButton);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            receptNaam.setText(extras.getString("naam"));
            receptBeschrijving.setText(extras.getString("beschrijving"));
        }

        receptenInfoBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                finish();
            }
        });



    }
}
