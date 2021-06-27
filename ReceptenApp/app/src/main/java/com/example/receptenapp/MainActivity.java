package com.example.receptenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        Recept[] recepten = new Recept[5];
        recepten[0] = new Recept("Lasagne","Lasagne beschrijving", 1);
        recepten[1] = new Recept("Lasagne","Lasagne beschrijving", 2);
        recepten[2] = new Recept("Lasagne","Lasagne beschrijving", 3);
        recepten[3] = new Recept("Lasagne","Lasagne beschrijving", 4);
        recepten[4] = new Recept("Lasagne","Lasagne beschrijving", 5);


        recyclerViewAdapter = new ReceptAdapter(recepten);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}