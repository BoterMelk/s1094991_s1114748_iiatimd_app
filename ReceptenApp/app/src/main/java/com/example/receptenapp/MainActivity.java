package com.example.receptenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements ReceptAdapter.RecyclerViewClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Recept[] recepten;
    private ReceptAdapter.RecyclerViewClickListener listener;
    private ImageButton loginButton;
    private ImageButton favorietenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        setReceptArray();
        setAdapter();

        loginButton = findViewById(R.id.profileActivityButton);
        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });

        favorietenButton = findViewById(R.id.favorietenActivityButton);
        favorietenButton.setOnClickListener(v -> {
            Intent favIntent = new Intent(this, FavorietenActivity.class);
            startActivity(favIntent);
        });
    }

    private void setAdapter() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerViewAdapter = new ReceptAdapter(recepten, this, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setReceptArray() {
        recepten = new Recept[10];
        recepten[0] = new Recept("Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", true, 1);
        recepten[1] = new Recept("Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", true, 2);
        recepten[2] = new Recept("Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", true, 3);
        recepten[3] = new Recept("Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", true, 4);
        recepten[4] = new Recept("Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", false, 5);
        recepten[5] = new Recept("Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", false, 6);
        recepten[6] = new Recept("Lasagne","Lasagne beschrijving 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1", "-tomaat, -iets anders, -gehakt", false, 7);
        recepten[7] = new Recept("La La La La La La La La La La Lasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", false, 8);
        recepten[8] = new Recept("LaLaLaLaLaLaLaLaLaLaLaLaLaLaLasagne","Lasagne beschrijving", "-tomaat, -iets anders, -gehakt", false, 9);
        recepten[9] = new Recept("Lasagne","Lasagne beschrijving nummer 10, vraag niet waarom alles lasagne is, want dat weet ik zelf ook niet.", "-tomaat, -iets anders, -gehakt", false, 10);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, ReceptInfoActivity.class);
        intent.putExtra("naam", recepten[position].getNaam());
        intent.putExtra("beschrijving", recepten[position].getBeschrijving());
        intent.putExtra("favoriet", recepten[position].isFavoriet());
        startActivity(intent);
    }
}

//    Als gebruiker wil ik een recept aan kunnen klikken om deze in detail te kunnen bekijken


//    Als gebruiker wil ik elke keer als de app opent, een nieuw recept kunnen zien
//    Als gebruiker wil ik kunnen inloggen
//    Als gebruiker wil ik een account kunnen aanmaken
//    Als gebruiker wil ik kunnen uitloggen
//    Als gebruiker wil ik getoonde recepten kunnen opslaan wanneer ik ben ingelogd
//    Als gebruiker wil ik mijn opgeslagen recepten kunnen bekijken wanneer ik ben ingelogd
//    Als gebruiker wil ik recepten kunnen zoeken door zoekwoorden in te typen in een zoekbalk
