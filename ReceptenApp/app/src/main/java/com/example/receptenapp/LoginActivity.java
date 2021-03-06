package com.example.receptenapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText editTextTextPassword, editTextTextEmailAddress;
    Button profileActivityButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        profileActivityButton = findViewById(R.id.profileActivityButton);

        profileActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editTextTextPassword.getText().toString().trim();
                String email = editTextTextEmailAddress.getText().toString().trim();

                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    loginWithEmail(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "please enter email/pass", Toast.LENGTH_SHORT).show();

                }

            }
        });

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    private void loginWithEmail(final String email, final String password) {
        String JSON_URL = "http://10.0.2.2:8000/api/login";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loginResponse(response, email);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responseV", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private void loginResponse(String response, String email) {
        JSONObject object = null;
        String token = "";
        try {
            object = new JSONObject(response);
            token = object.getString("token");
        } catch (Exception e) {
        }

        UtitlityClass.setLoginId(LoginActivity.this, email);
        UtitlityClass.setToken(LoginActivity.this, token);

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
