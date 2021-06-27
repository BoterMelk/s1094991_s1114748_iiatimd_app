package com.example.receptenapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ReceptAdapter extends RecyclerView.Adapter<ReceptAdapter.ReceptViewHolder>{

    private Recept[] recept;

    public ReceptAdapter(Recept[] recept) {
        this.recept = recept;
    }

    public static class ReceptViewHolder extends RecyclerView.ViewHolder {
        public TextView receptNaam;
        public TextView receptBeschrijving;

        public ReceptViewHolder(View v) {
            super(v);
            receptNaam = v.findViewById(R.id.receptNaam);
            receptBeschrijving = v.findViewById(R.id.receptBeschrijving);
        }
    }

    @NonNull
    @Override
    public ReceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recept_card,parent,false);
        ReceptViewHolder receptViewHolder = new ReceptViewHolder(v);
        return receptViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceptViewHolder holder, int position) {
        holder.receptNaam.setText(recept[position].getNaam());
        holder.receptBeschrijving.setText(recept[position].getBeschrijving());
    }

    @Override
    public int getItemCount() {
        return recept.length;
    }
}
