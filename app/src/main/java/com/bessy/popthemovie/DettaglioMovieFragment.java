package com.bessy.popthemovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bessy.popthemovie.adapters.RecyclerFilmAdapter;
import com.bessy.popthemovie.databinding.FragmentDettaglioMovieBinding;
import com.bessy.popthemovie.databinding.FragmentListeUtenteBinding;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DettaglioMovieFragment extends Fragment {
    private static final String TAG = "DettaglioMovieFragment";
    private static DettaglioMovieFragment instance;
    private FragmentDettaglioMovieBinding binding;
    private MainActivityViewModel viewModel;

    public DettaglioMovieFragment() {
        // Required empty public constructor
    }

    public static synchronized DettaglioMovieFragment newInstance() {
        if(instance == null){
            instance = new DettaglioMovieFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDettaglioMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        MutableLiveData<MovieAPIResponse> movieLiveData = viewModel.getLastMovie();
        if(movieLiveData.getValue()== null){
            binding.titoloTextViewDettaglio.setText("film non trovato");
        }
        else{
            MovieAPIResponse movie = movieLiveData.getValue();
            binding.titoloTextViewDettaglio.setText(movie.getTitle());
            binding.genereTextViewDettaglio.setText(movie.getGenre());
            binding.tramaTextViewDettaglio.setText(movie.getPlot());
            Picasso.get().load(movie.getPoster()).into(binding.posterImageViewDettaglio);
        }

    }
}