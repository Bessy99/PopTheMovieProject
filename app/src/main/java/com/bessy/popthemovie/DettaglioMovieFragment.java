package com.bessy.popthemovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bessy.popthemovie.adapters.RecyclerMovieAPIResponse;
import com.bessy.popthemovie.databinding.FragmentDettaglioMovieBinding;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DettaglioMovieFragment extends Fragment {
    private static final String TAG = "DettaglioMovieFragment";
    private static DettaglioMovieFragment instance;
    private FragmentDettaglioMovieBinding binding;
    private MainActivityViewModel viewModel;
    private RecyclerMovieAPIResponse recyclerMovieAPIResponse;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDettaglioMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        LinearLayoutManager layoutManagerDettaglio = new LinearLayoutManager(getActivity());
        binding.recyclerDettaglio.setLayoutManager(layoutManagerDettaglio);
        recyclerMovieAPIResponse = new RecyclerMovieAPIResponse(getActivity(),null);
        binding.recyclerDettaglio.setAdapter(recyclerMovieAPIResponse);

        Observer<MovieAPIResponse> observerMovie = new Observer<MovieAPIResponse>() {
            @Override
            public void onChanged(MovieAPIResponse movieAPIResponse) {
                if(movieAPIResponse!=null) {
                    List<MovieAPIResponse> movie = new ArrayList<>();
                    movie.add(movieAPIResponse);
                    recyclerMovieAPIResponse.setData(movie);
                }
            }
        };

        viewModel.getLastMovie().observe(getViewLifecycleOwner(),observerMovie);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.getLastMovie().postValue(null);
    }
}