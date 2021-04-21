package com.bessy.popthemovie.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.repositories.MovieRepository;
import com.bessy.popthemovie.repositories.UserRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<MovieAPIResponse> movie;
    private MutableLiveData<User> user;

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

    public MutableLiveData<User> getUser(long id){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        UserRepository.getInstance().getUser(user, id);
        return user;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }
}
