package com.bessy.popthemovie;

import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bessy.popthemovie.databinding.FragmentSimilarMovieBinding;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.seismic.ShakeDetector;

import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

public class SimilarMovieFragment extends Fragment {

    private static final String TAG = "SimilarMovieFragment";
    private static SimilarMovieFragment instance;
    private FragmentSimilarMovieBinding binding;
    private SensorManager sm;
    private ShakeDetector sd;
    private MainActivityViewModel viewModel;
    private List<Movie> classificaFilmList;
    private int position;
    private long lastShake=0;


    public SimilarMovieFragment() {
        // Required empty public constructor
    }

    public static synchronized SimilarMovieFragment newInstance() {
        if(instance == null){
            instance = new SimilarMovieFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSimilarMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Affinità");
        position = 0;
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        viewModel.getClassificaFilm();
        Observer<List<Movie>> observerClassificaFilm = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> classificaFilm) {
                if(classificaFilm!=null) {
                    classificaFilmList = classificaFilm;
                    Log.d(TAG, "numero film classifica: "+classificaFilmList.size());
                }
                else {
                    Log.d(TAG, "niente");
                }
            }
        };
        viewModel.getClassificaFilm().observe(getViewLifecycleOwner(),observerClassificaFilm);

        sm = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sd = new ShakeDetector(new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                if (classificaFilmList != null && classificaFilmList.size() > 0) {
                    if (separateShake()) {
                        Log.d(TAG, "shakee!" + position);
                        lastShake = System.currentTimeMillis();
                        if (position >= classificaFilmList.size()) {
                            position = 0;
                        }
                        bind(classificaFilmList.get(position));
                        position++;
                    }
                }
                else binding.titoloTextViewSimilar.setText("i film suggeriti sono terminati");
            }
        });
        sd.start(sm);

    }

    public void bind(Movie movie){
        binding.titoloTextViewSimilar.setText(movie.getTitolo());
        binding.genereTextViewSimilar.setText(movie.getGenere());
        binding.durataTextViewSimilar.setText(movie.getDurata());
        Picasso.get().load(movie.getPoster()).into(binding.posterImageViewSimilar);
        binding.addDaVedereButtonSimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addFilmDaVedere(movie);
            }
        });
        binding.addVistiButtonSimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addFilmVisto(movie);
            }
        });

    }

    public boolean separateShake(){
        return ((System.currentTimeMillis() - lastShake) > 3000) ? true : false;
    }

    @Override
    public void onResume() {
        super.onResume();
        sd.start(sm);
    }

    @Override
    public void onPause() {
        super.onPause();
        sd.stop();
    }

}