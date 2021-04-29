package com.bessy.popthemovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bessy.popthemovie.adapters.RecyclerFilmAdapter;
import com.bessy.popthemovie.databinding.FragmentFilmDaVedereBinding;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.utils.Constants;


import java.util.List;


public class FilmDaVedereFragment extends Fragment {

    private static final String TAG = "FilmDaVedereFragment";
    private static FilmDaVedereFragment instance;
    private FragmentFilmDaVedereBinding binding;
    private RecyclerFilmAdapter recyclerFilmAdapter;
    private MainActivityViewModel viewModel;


    public FilmDaVedereFragment() {
        // Required empty public constructor
    }

    public static synchronized FilmDaVedereFragment newInstance() {
        if(instance == null){
            instance = new FilmDaVedereFragment();
        }
        return instance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmDaVedereBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.filmDaVedereRecyclerView.setLayoutManager(layoutManager);

        recyclerFilmAdapter = new RecyclerFilmAdapter(getActivity(), getMovieSet());
        binding.filmDaVedereRecyclerView.setAdapter(recyclerFilmAdapter);

        Observer<User> observer = user -> recyclerFilmAdapter.setData(user.getFilmDaVedere());

        viewModel.getUser().observe(getViewLifecycleOwner(), observer);
    }

    private List<Movie> getMovieSet(){
        MutableLiveData<User> user = viewModel.getUser();
        if(user.getValue() != null)
            return user.getValue().getFilmDaVedere();
        else return null;
    }
}