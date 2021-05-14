package com.bessy.popthemovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bessy.popthemovie.MainActivity;
import com.bessy.popthemovie.R;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerFilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;
    private MainActivityViewModel viewModel;

    public RecyclerFilmAdapter(FragmentActivity context, List<Movie> movieList) {
        this.movieList = movieList;
        this.layoutInflater = LayoutInflater.from(context);
        this.viewModel = new ViewModelProvider(context).get(MainActivityViewModel.class);
    }

    public void setData(List<Movie> movieSet) {
        if(movieSet != null) {
            this.movieList = movieSet;
            notifyDataSetChanged();
        }
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder{
        ImageView posterImageView;
        TextView titoloTextView;
        TextView durataTextView;
        TextView genereTextView;
        ImageButton dettagliButton;

        public FilmViewHolder(View view){
            super(view);
            posterImageView = view.findViewById(R.id.posterImageView);
            titoloTextView = view.findViewById(R.id.titoloItem);
            genereTextView = view.findViewById(R.id.genereItem);
            durataTextView = view.findViewById(R.id.durataItem);
            dettagliButton = view.findViewById(R.id.buttonVediDettagli);
        }

        public void bind(Movie movieNext, MainActivityViewModel viewModel) {
            posterImageView.setContentDescription(movieNext.getTitolo());
            Picasso.get().load(movieNext.getPoster()).into(posterImageView);
            titoloTextView.setText(movieNext.getTitolo());
            genereTextView.setText(movieNext.getGenere());
            if(movieNext.getDurata()!=null)
                durataTextView.setText(movieNext.getDurata());
            else durataTextView.setText("durata sconosciuta");
            dettagliButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.getMovieByTitle(movieNext.getTitolo());
                    Navigation.findNavController(v).navigate(R.id.action_listeUtenteFragment_to_dettaglioMovieFragment);
                }
            });
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
         ((FilmViewHolder) holder).bind(movieList.get(position), viewModel);
    }

    @Override
    public int getItemCount() {
        if(movieList == null)
            return 0;
        else return movieList.size();
    }
}
