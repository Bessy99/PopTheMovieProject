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

import com.bessy.popthemovie.adapters.RecyclerFilmAdapter;
import com.bessy.popthemovie.databinding.FragmentListeUtenteBinding;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;

import org.jetbrains.annotations.NotNull;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListeUtenteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        LinearLayoutManager layoutManagerDaVedere = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerVisti = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        binding.listaFilmDaVedereRecyclerView.setLayoutManager(layoutManagerDaVedere);
        binding.listaFilmVistiRecyclerView.setLayoutManager(layoutManagerVisti);

        recyclerFilmDaVedereAdapter = new RecyclerFilmAdapter(getActivity(), null);
        binding.listaFilmDaVedereRecyclerView.setAdapter(recyclerFilmDaVedereAdapter);

        recyclerFilmVistiAdapter = new RecyclerFilmAdapter(getActivity(), null);
        binding.listaFilmVistiRecyclerView.setAdapter(recyclerFilmVistiAdapter);

        Observer<User> observerVisti = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                recyclerFilmVistiAdapter.setData(user.getFilmVisti());
            }
        };

        Observer<User> observerDaVedere = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                recyclerFilmDaVedereAdapter.setData(user.getFilmDaVedere());
            }
        };

        viewModel.getUser().observe(getViewLifecycleOwner(), observerDaVedere);
        viewModel.getUser().observe(getViewLifecycleOwner(), observerVisti);
    }
}