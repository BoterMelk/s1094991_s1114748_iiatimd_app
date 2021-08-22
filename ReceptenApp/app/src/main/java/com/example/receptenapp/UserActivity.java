package com.example.receptenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    TextView userEmailTextView;
    private Button buttonLogout;

    private String email;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        email = UtitlityClass.getLoginId(UserActivity.this);
        token = UtitlityClass.getToken(UserActivity.this);

        userEmailTextView = (TextView)findViewById(R.id.userEmail);
        userEmailTextView.setText(email);

        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(v -> {
            UtitlityClass.logout(UserActivity.this);
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        });
    }
}