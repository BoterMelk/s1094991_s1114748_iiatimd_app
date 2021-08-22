package com.example.receptenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity_empty extends AppCompatActivity {

    TextView loggin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_empty);

        loggin = findViewById(R.id.buttonLogin);

        loggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity_empty.this, LoginActivity.class));
                finish();
            }
        });

    }


}
