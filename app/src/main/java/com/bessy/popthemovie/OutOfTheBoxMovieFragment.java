package com.bessy.popthemovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bessy.popthemovie.adapters.RecyclerFilmAdapter;
import com.bessy.popthemovie.databinding.FragmentListeUtenteBinding;
import com.bessy.popthemovie.databinding.FragmentOutOfTheBoxMovieBinding;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;

import java.util.Iterator;
import java.util.List;

public class OutOfTheBoxMovieFragment extends Fragment {

    private static final String TAG = "OutOfBoxMovieFragment";
    private static OutOfTheBoxMovieFragment instance;
    private FragmentOutOfTheBoxMovieBinding binding;
    private MainActivityViewModel viewModel;
    private List<Movie> classificaFilmList;
    private Iterator<Movie> movieIterator;

    public OutOfTheBoxMovieFragment() {
        // Required empty public constructor
    }

    public static synchronized OutOfTheBoxMovieFragment newInstance() {
        if(instance == null){
            instance = new OutOfTheBoxMovieFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        viewModel.getClassificaFilm();

        Observer<List<Movie>> classificaObserver = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                classificaFilmList = movies;
                movieIterator = classificaFilmList.iterator();
                while(movieIterator.hasNext()) {
                    Log.d(TAG, movieIterator.next().getTitolo());
                }
            }
        };
        viewModel.getClassificaFilm().observe(getViewLifecycleOwner(), classificaObserver);

        // Inflate the layout for this fragment
        binding = FragmentOutOfTheBoxMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}