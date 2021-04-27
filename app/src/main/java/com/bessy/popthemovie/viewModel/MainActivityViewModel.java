package com.bessy.popthemovie.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.User;
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
            Log.d("view model", "entra");
        }
        UserRepository.getInstance().getUser(user, email);
        return user;
    }

    public MutableLiveData<User> getUser(){
        if(user == null){
            user = new MutableLiveData<User>();
            Log.d("view model", "entra");
        }
        return user;
    }

    public MutableLiveData<User> saveUser(User userToSave){

        UserRepository.getInstance().saveUser(user, userToSave);
        return user;
    }

}
