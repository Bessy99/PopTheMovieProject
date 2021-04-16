package com.bessy.popthemovie.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.repositories.MovieRepository;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieAPIResponse> movie;

    public MutableLiveData<MovieAPIResponse> getMovie(String title){
        if(movie == null){
            movie = new MutableLiveData<MovieAPIResponse>();
        }
        MovieRepository.getInstance().getMovie(movie, title);
        return movie;
    }

    public MutableLiveData<MovieAPIResponse> getMovie() {
        return movie;
    }
}
