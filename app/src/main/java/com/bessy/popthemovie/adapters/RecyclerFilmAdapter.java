package com.bessy.popthemovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bessy.popthemovie.R;
import com.bessy.popthemovie.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerFilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;

    public RecyclerFilmAdapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<Movie> movieSet) {
        if(movieSet != null) {
            this.movieList = movieSet;
            notifyDataSetChanged();
        }
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder{
        TextView titoloTextView;
        TextView genereTextView;
        ImageView posterImageView;

        public FilmViewHolder(View view){
            super(view);
            titoloTextView = view.findViewById(R.id.titoloTextView);
            genereTextView = view.findViewById(R.id.genereTextView);
            posterImageView = view.findViewById(R.id.posterImageView);
        }

        public void bind(Movie movieNext) {
            titoloTextView.setText(movieNext.getTitolo());
            genereTextView.setText(movieNext.getGenere());
            Picasso.get().load(movieNext.getPoster()).into(posterImageView);
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
         ((FilmViewHolder) holder).bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        if(movieList == null)
            return 0;
        else return movieList.size();
    }
}
