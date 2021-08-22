package com.example.receptenapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.receptenapp.database.DataSource;
import com.example.receptenapp.database.model.Favourite;
import com.example.receptenapp.database.model.Recipe;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ReceptAdapter extends RecyclerView.Adapter<ReceptAdapter.ReceptViewHolder> {

    private ArrayList<Recipe> recepten;
    private RecyclerViewClickListener listener;
    private Context context;
    DataSource dataSource;

    public ReceptAdapter(ArrayList<Recipe> recepten, RecyclerViewClickListener listener, Context context) {
        this.recepten = recepten;
        this.listener = listener;
        this.context = context;
        dataSource = new DataSource(context);
    }

    @NonNull
    @Override
    public ReceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recept_card, parent, false);
        ReceptViewHolder receptViewHolder = new ReceptViewHolder(v, listener);
        return receptViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceptViewHolder holder, int position) {
        holder.receptNaam.setText(recepten.get(position).getTitle());

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5);
        circularProgressDrawable.setCenterRadius(65);
        circularProgressDrawable.start();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.receptBeschrijving.setText(Html.fromHtml(recepten.get(position).getSummary(), Html.FROM_HTML_MODE_LEGACY));

        } else {
            holder.receptBeschrijving.setText(Html.fromHtml("<html><body>" + recepten.get(position).getSummary() + "</body></html>"));
        }

        Glide.with(context).load(recepten.get(position).getImage()).apply(new RequestOptions().placeholder(circularProgressDrawable).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).into(holder.imageView);

//        holder.receptBeschrijving.setText(recepten.get(position).getSummary());
        holder.receptFavorietButton.setChecked(recepten.get(position).isFavoriet());

        if (holder.receptFavorietButton.isChecked() == false) {
            holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_white));
        } else {
            holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_black));
        }

        holder.receptFavorietButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_black));
                recepten.get(position).setFavoriet(true);

                Favourite mFavourite = new Favourite();
                mFavourite.setId(recepten.get(position).getId());
                mFavourite.setFavourite(1);
                mFavourite.setUserid(UtitlityClass.getLoginId(context));
                dataSource.addFavProduct(mFavourite);


            } else {
                holder.receptFavorietButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.heart_white));
                recepten.get(position).setFavoriet(false);

                dataSource.removeFavProducts(recepten.get(position).getId(), UtitlityClass.getLoginId(context));

                recepten.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recepten.size();
    }

    public class ReceptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView receptNaam;
        public TextView receptBeschrijving;
        public ToggleButton receptFavorietButton;
        RecyclerViewClickListener recyclerViewClickListener;
        public ImageView imageView;

        public ReceptViewHolder(@NonNull View v, RecyclerViewClickListener recyclerViewClickListener) {
            super(v);
            receptNaam = v.findViewById(R.id.receptNaam);
            receptBeschrijving = v.findViewById(R.id.receptBeschrijving);
            receptFavorietButton = v.findViewById(R.id.receptFavorietButton);
            this.recyclerViewClickListener = recyclerViewClickListener;
            imageView = v.findViewById(R.id.imageView);
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
