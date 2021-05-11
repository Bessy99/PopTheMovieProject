package com.bessy.popthemovie;

import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bessy.popthemovie.adapters.RecyclerFilmAdapter;
import com.bessy.popthemovie.databinding.FragmentListeUtenteBinding;
import com.bessy.popthemovie.databinding.FragmentOutOfTheBoxMovieBinding;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.seismic.ShakeDetector;

import java.util.Iterator;
import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

public class OutOfTheBoxMovieFragment extends Fragment {

    private static final String TAG = "OutOfBoxMovieFragment";
    private static OutOfTheBoxMovieFragment instance;
    private FragmentOutOfTheBoxMovieBinding binding;
    private MainActivityViewModel viewModel;
    private List<Movie> classificaFilmList;
    private int position = 0;
    private SensorManager sm;
    private ShakeDetector sd;
    private long lastShake = 0;

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
        // Inflate the layout for this fragment
        binding = FragmentOutOfTheBoxMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Out of the box!");
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        viewModel.getClassificaFilm();
        Observer<List<Movie>> observerClassificaFilm = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> classificaFilm) {
                if(classificaFilm!=null && classificaFilm.size()>0) {
                    classificaFilmList = classificaFilm;
                    position = classificaFilmList.size()-1;
                    Log.d(TAG, "numero film classifica: "+position);
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
                if (classificaFilmList != null && classificaFilmList.size()>0) {
                    if (separateShake()) {
                        Log.d(TAG, "shakee!" + position);
                        lastShake = System.currentTimeMillis();
                        if (position < 0)
                        { position = classificaFilmList.size() - 1;}

                        bind(classificaFilmList.get(position));
                        position--;
                    }
                }
                else {
                    binding.titoloTextViewOTB.setText("nessun suggerimento");
                }
            }
        });
        sd.start(sm);

    }

    public void bind(Movie movie){
        binding.titoloTextViewOTB.setText(movie.getTitolo());
        binding.genereTextViewOTB.setText(movie.getGenere());
        Picasso.get().load(movie.getPoster()).into(binding.posterImageViewOTB);

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

    public boolean separateShake(){
        return ((System.currentTimeMillis() - lastShake) > 3000) ? true : false;
    }
}