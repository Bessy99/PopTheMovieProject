package com.bessy.popthemovie;

import android.content.MutableContextWrapper;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bessy.popthemovie.databinding.FragmentDettaglioMovieBinding;
import com.bessy.popthemovie.databinding.FragmentSimilarMovieBinding;
import com.bessy.popthemovie.models.FilmMaiVistoUtente;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.seismic.ShakeDetector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.Context.SENSOR_SERVICE;
import static android.view.Gravity.CENTER;

public class SimilarMovieFragment extends Fragment {

    private static final String TAG = "SimilarMovieFragment";
    private static SimilarMovieFragment instance;
    private FragmentSimilarMovieBinding binding;
    private SensorManager sm;
    private ShakeDetector sd;
    private MainActivityViewModel viewModel;
    List<FilmMaiVistoUtente> listaFilmMaiVisti;
    int position;

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
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        viewModel.getFilmMaiVisti();
        position = 0;
        sm = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sd = new ShakeDetector(new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                Log.d(TAG, "shakee!");
                if(position<listaFilmMaiVisti.size())
                {
                  viewModel.getMovieById(listaFilmMaiVisti.get(position).getFilm_id());
                  position ++;
                }
                else
                    binding.titoloTextViewSimilar.setText("i film suggeriti sono terminati");

            }
        });
        Observer<List<FilmMaiVistoUtente>> observerFilmMaiVisti = new Observer<List<FilmMaiVistoUtente>>() {
            @Override
            public void onChanged(List<FilmMaiVistoUtente> filmMaiVistiList) {
                if(filmMaiVistiList!=null) {
                        listaFilmMaiVisti = filmMaiVistiList;
                        Log.d(TAG, listaFilmMaiVisti.get(position).getEmail());
                    }
                    else {
                        Log.d(TAG, "niente");
                        listaFilmMaiVisti = new ArrayList<FilmMaiVistoUtente>();
                        listaFilmMaiVisti.add(new FilmMaiVistoUtente());
                    }
                }
        };

        viewModel.getFilmMaiVisti().observe(getViewLifecycleOwner(),observerFilmMaiVisti);

        Observer<MovieAPIResponse> observeMovieAnswer = new Observer<MovieAPIResponse>() {
            @Override
            public void onChanged(MovieAPIResponse movie) {
                if(movie != null){
                    binding.titoloTextViewSimilar.setText(movie.getTitle());
                    binding.genereTextViewSimilar.setText(movie.getGenre());
                    binding.tramaTextViewSimilar.setText(movie.getPlot());
                    Picasso.get().load(movie.getPoster()).into(binding.posterImageViewSimilar);
                }
                else {
                    position ++;
                    if(position+1 < listaFilmMaiVisti.size())
                        viewModel.getMovieById(listaFilmMaiVisti.get(position).getFilm_id());
                }
            }
        };

        viewModel.getLastMovie().observe(getViewLifecycleOwner(),observeMovieAnswer);

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