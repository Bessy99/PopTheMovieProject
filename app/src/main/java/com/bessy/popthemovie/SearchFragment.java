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
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Iterator;
import java.util.List;

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
                if(viewModel.getLastMovie().getValue()!=null && viewModel.getLastMovie().getValue().getImdbID()!=null) {
                    if (controllo()) {
                        viewModel.AddFilmVisto(viewModel.getLastMovie().getValue());
                        Snackbar.make(v, "Film aggiunto alla lista dei film visti!", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(v, "Il film è già presente nelle tue liste!", Snackbar.LENGTH_SHORT).show();
                    }
                    viewModel.getLastMovie().postValue(new MovieAPIResponse());
                }
                else Snackbar.make(v, "Cerca un film che hai visto!", Snackbar.LENGTH_SHORT).show();
            }
        });

        Button addDaVedereButton = binding.addDaVedereButton;
        addDaVedereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getLastMovie().getValue()!=null && viewModel.getLastMovie().getValue().getImdbID()!=null) {
                    if (controllo()) {
                        viewModel.AddFilmDaVedere(viewModel.getLastMovie().getValue());
                        Snackbar.make(v, "Film aggiunto alla lista dei film da vedere!", Snackbar.LENGTH_SHORT).show();

                    } else {
                        Snackbar.make(v, "Il film è già presente nelle tue liste!", Snackbar.LENGTH_SHORT).show();
                    }
                    viewModel.getLastMovie().postValue(new MovieAPIResponse());
                }
                else Snackbar.make(v, "Cerca un film da vedere!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private boolean controllo() {
        String imbdId = viewModel.getLastMovie().getValue().getImdbID();
        List<Movie> filmVisti = viewModel.getFilmVisti();
        List<Movie> filmDaVedere = viewModel.getFilmDaVedere();
        boolean nonPresente = true;
        Iterator<Movie> iVisti = filmVisti.iterator();
        Iterator<Movie> iDaVedere = filmDaVedere.iterator();
        while (nonPresente && iVisti.hasNext()) {
            Movie m = iVisti.next();
            if (m != null) {
                if (imbdId.equals(m.getId())) {
                    nonPresente = false;
                }
            }
        }
        while (nonPresente && iDaVedere.hasNext()) {
            Movie m = iDaVedere.next();
            if (m != null) {
                if (imbdId.equals(m.getId())) {
                    nonPresente = false;
                }
            }
        }
        return nonPresente;
    }
}