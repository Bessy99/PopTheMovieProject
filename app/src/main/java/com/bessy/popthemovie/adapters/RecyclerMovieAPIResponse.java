package com.bessy.popthemovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bessy.popthemovie.R;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerMovieAPIResponse extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieAPIResponse> movie;
    private LayoutInflater layoutInflater;

    public RecyclerMovieAPIResponse(Context context, List<MovieAPIResponse> movie) {
        this.movie = movie;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<MovieAPIResponse> movie) {
        if(movie != null) {
            this.movie = movie;
            notifyDataSetChanged();
        }
    }

    public static class DettaglioViewHolder extends RecyclerView.ViewHolder{
        TextView titoloTextView;
        TextView genereTextView;
        TextView plotTextView;
        ImageView posterImageView;
        RatingBar ratingBar;

        public DettaglioViewHolder(View view){
            super(view);
            titoloTextView =  view.findViewById(R.id.titoloTextViewDettaglio);
            genereTextView = view.findViewById(R.id.genereTextViewDettaglio);
            plotTextView = view.findViewById(R.id.tramaTextViewDettaglio);
            posterImageView = view.findViewById(R.id.posterImageViewDettaglio);
            ratingBar = view.findViewById(R.id.ratingBar);
        }

        public void bind(MovieAPIResponse movieNext) {
            titoloTextView.setText(movieNext.getTitle());
            plotTextView.setText(movieNext.getPlot());
            genereTextView.setText(movieNext.getGenre());
            Picasso.get().load(movieNext.getPoster()).into(posterImageView);
            ratingBar.setIsIndicator(false);
            ratingBar.setRating(movieNext.getRating());
            ratingBar.setIsIndicator(true);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.card_dettaglio, parent, false);
        return new RecyclerMovieAPIResponse.DettaglioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecyclerMovieAPIResponse.DettaglioViewHolder) holder).bind(movie.get(position));
    }

    @Override
    public int getItemCount() {
        if(movie == null )
            return 0;
        else return movie.size();
    }
}
