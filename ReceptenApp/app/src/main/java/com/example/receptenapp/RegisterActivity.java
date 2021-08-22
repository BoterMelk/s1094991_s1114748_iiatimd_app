package com.example.receptenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextTextName, editTextTextPassword, editTextTextPassword_confirm, editTextTextEmailAddress;
    Button profileActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextTextName = findViewById(R.id.editTextTextName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword_confirm = findViewById(R.id.editTextTextPassword_confirm);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        profileActivityButton = findViewById(R.id.profileActivityButton);

        profileActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextTextName.getText().toString().trim();
                String email = editTextTextEmailAddress.getText().toString().trim();
                String password = editTextTextPassword.getText().toString().trim();
                String password_confirm = editTextTextPassword_confirm.getText().toString().trim();

                if (name.trim().length() > 0 && password.trim().length() > 0 && email.trim().length() > 0 && password_confirm.trim().length() > 0) {
                    if (password.equals(password_confirm)) {
                        registerWithEmail(name, email, password);

                    } else {
                        Toast.makeText(RegisterActivity.this, "Confirm password must match with password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "please enter name/email/pass", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void registerWithEmail(String name, final String email, final String password) {
        String JSON_URL = "http://10.0.2.2:8000/api/register/";
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
                params.put("password_confirmation", password);
                params.put("name", name);

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

        UtitlityClass.setLoginId(RegisterActivity.this, email);
        UtitlityClass.setToken(RegisterActivity.this, token);

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
