package com.bessy.popthemovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.bessy.popthemovie.databinding.FragmentSearchBinding;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    private static SearchFragment instance;
    private FragmentSearchBinding binding;
    private MainActivityViewModel viewModel;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static synchronized SearchFragment newInstance() {
        if(instance == null){
            instance = new SearchFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Search");
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.getMovieByTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Button addVistiButton = binding.addVistiButton;
        addVistiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.AddFilmVisto(viewModel.getLastMovie().getValue());
            }
        });

        Button addDaVedereButton = binding.addDaVedereButton;
        addDaVedereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.AddFilmDaVedere(viewModel.getLastMovie().getValue());
            }
        });


    }

}