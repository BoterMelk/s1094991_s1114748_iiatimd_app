package com.example.receptenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.net.CookieHandler;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity implements ReceptAdapter.RecyclerViewClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Recept[] recepten;
    private ReceptAdapter.RecyclerViewClickListener listener;
    private ImageButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        setReceptArray();
        setAdapter();

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> openNewActivity());
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void setAdapter() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerViewAdapter = new ReceptAdapter(recepten, listener);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setReceptArray() {
        recepten = new Recept[5];
        recepten[0] = new Recept("Lasagne","Lasagne beschrijving", 1);
        recepten[1] = new Recept("Lasagne","Lasagne beschrijving", 2);
        recepten[2] = new Recept("Lasagne","Lasagne beschrijving", 3);
        recepten[3] = new Recept("Lasagne","Lasagne beschrijving", 4);
        recepten[4] = new Recept("Lasagne","Lasagne beschrijving", 5);
    }

    @Override
    public void onClick(int position) {

        recepten[position].getNaam();
        Intent intent = new Intent(this, ReceptActivity.class);
        intent.putExtra("naam", "Test");
        startActivity(intent);
//        recepten[position].getNaam()
    }
}