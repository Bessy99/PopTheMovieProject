package com.bessy.popthemovie.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bessy.popthemovie.R;
import com.bessy.popthemovie.models.Movie;

import java.util.Set;

public class RecyclerFilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Set<Movie> movieSet;
    private LayoutInflater layoutInflater;

    public RecyclerFilmAdapter(Context context, Set<Movie> movieSet) {
        this.movieSet = movieSet;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(Set<Movie> movieSet) {
        if(movieSet != null) {
            this.movieSet = movieSet;
            notifyDataSetChanged();
        }
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder{
        TextView titoloTextView;

        public FilmViewHolder(View view){
            super(view);
            titoloTextView = view.findViewById(R.id.titoloTextView);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(movieSet.isEmpty())
            return 0;
        else return movieSet.size();
    }
}
