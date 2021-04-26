package com.bessy.popthemovie.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.repositories.MovieAPIRepository;
import com.bessy.popthemovie.repositories.UserRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<MovieAPIResponse> movie;
    private MutableLiveData<User> user;

    public MutableLiveData<MovieAPIResponse> getMovie(String title){
        if(movie == null){
            movie = new MutableLiveData<MovieAPIResponse>();
        }
        MovieAPIRepository.getInstance().getMovie(movie, title);
        return movie;
    }

    public MutableLiveData<User> getUser(String email){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        UserRepository.getInstance().getUser(user, email);
        return user;
    }

    public MutableLiveData<User> saveUser(User userToSave){

        UserRepository.getInstance().saveUser(user, userToSave);
        return user;
    }

    //---------------------------------------------> Getter
    public MutableLiveData<MovieAPIResponse> getMovie() {
        return movie;
    }
    public MutableLiveData<User> getUser() {
        return user;
    }
}
