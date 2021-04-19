package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bessy.popthemovie.databinding.ActivityMainBinding;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /*
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        MutableLiveData<MovieAPIResponse> movieLiveData = movieViewModel.getMovie("hunger");
        */


    }
}