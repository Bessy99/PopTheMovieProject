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
import com.bessy.popthemovie.databinding.FragmentListeUtenteBinding;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;

import java.util.List;

public class ListeUtenteFragment extends Fragment {

    private static final String TAG = "ListeUtenteFragment";
    private static ListeUtenteFragment instance;
    private FragmentListeUtenteBinding binding;
    private RecyclerFilmAdapter recyclerFilmDaVedereAdapter;
    private RecyclerFilmAdapter recyclerFilmVistiAdapter;
    private MainActivityViewModel viewModel;


    public ListeUtenteFragment() {

    }

    public static synchronized ListeUtenteFragment newInstance() {
        if(instance == null){
            instance = new ListeUtenteFragment();
        }
        return instance;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListeUtenteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManagerDaVedere = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerVisti = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        binding.listaFilmDaVedereRecyclerView.setLayoutManager(layoutManagerDaVedere);
        binding.listaFilmVistiRecyclerView.setLayoutManager(layoutManagerVisti);

        recyclerFilmDaVedereAdapter = new RecyclerFilmAdapter(getActivity(), getMovieDaVedere());
        binding.listaFilmDaVedereRecyclerView.setAdapter(recyclerFilmDaVedereAdapter);

        recyclerFilmVistiAdapter = new RecyclerFilmAdapter(getActivity(), getMovieVisti());
        binding.listaFilmVistiRecyclerView.setAdapter(recyclerFilmVistiAdapter);

        Observer<User> observerDaVedere = user -> recyclerFilmDaVedereAdapter.setData(user.getFilmDaVedere());
        Observer<User> observerVisti = user -> recyclerFilmVistiAdapter.setData(user.getFilmVisti());

        viewModel.getUser().observe(getViewLifecycleOwner(), observerDaVedere);
        viewModel.getUser().observe(getViewLifecycleOwner(), observerVisti);
    }

    private List<Movie> getMovieDaVedere(){
        MutableLiveData<User> user = viewModel.getUser();
        if(user.getValue() != null)
            return user.getValue().getFilmDaVedere();
        else return null;
    }

    private List<Movie> getMovieVisti(){
        MutableLiveData<User> user = viewModel.getUser();
        if(user.getValue() != null)
            return user.getValue().getFilmVisti();
        else return null;
    }
}