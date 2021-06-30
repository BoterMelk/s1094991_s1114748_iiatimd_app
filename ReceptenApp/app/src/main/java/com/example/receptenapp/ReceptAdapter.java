package com.example.receptenapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

public class ReceptAdapter extends RecyclerView.Adapter<ReceptAdapter.ReceptViewHolder>{

    private Recept[] recepten;
    private RecyclerViewClickListener listener;
    private Context context;

    public ReceptAdapter(Recept[] recepten, RecyclerViewClickListener listener, Context context) {
        this.recepten = recepten;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recept_card,parent,false);
        ReceptViewHolder receptViewHolder = new ReceptViewHolder(v, listener);
        return receptViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceptViewHolder holder, int position) {
        holder.receptNaam.setText(recepten[position].getNaam());
        holder.receptBeschrijving.setText(recepten[position].getBeschrijving());
        holder.receptFavorietButton.setChecked(recepten[position].isFavoriet());

        if(holder.receptFavorietButton.isChecked() == false) {
            holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_white) );
        } else {
            holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_black));
        }

        holder.receptFavorietButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_black));
                recepten[position].setFavoriet(true);
            }else {
                holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_white));
                recepten[position].setFavoriet(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recepten.length;
    }

    public class ReceptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView receptNaam;
        public TextView receptBeschrijving;
        public ToggleButton receptFavorietButton;
        RecyclerViewClickListener recyclerViewClickListener;

        public ReceptViewHolder(@NonNull View v, RecyclerViewClickListener recyclerViewClickListener) {
            super(v);
            receptNaam = v.findViewById(R.id.receptNaam);
            receptBeschrijving = v.findViewById(R.id.receptBeschrijving);
            receptFavorietButton = v.findViewById(R.id.receptFavorietButton);
            this.recyclerViewClickListener = recyclerViewClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(int position);
    }
}
