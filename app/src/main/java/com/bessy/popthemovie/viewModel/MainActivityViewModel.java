package com.bessy.popthemovie.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.repositories.MovieAPIRepository;
import com.bessy.popthemovie.repositories.UserRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<User> user;
    private MutableLiveData<MovieAPIResponse> movieAPIResponse;

    //------------------> User
    public MutableLiveData<User> getUser(String email, String password){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        UserRepository.getInstance().getUser(user, email, password);
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
    //--------------------

    //--------------------> Movie API Response

    public MutableLiveData<MovieAPIResponse> getMovieByTitle(String title){
        if(movieAPIResponse == null){
            movieAPIResponse = new MutableLiveData<MovieAPIResponse>();
        }
        MovieAPIRepository.getInstance().getMovie(movieAPIResponse, title);
        return movieAPIResponse;
    }

    public MutableLiveData<MovieAPIResponse> getLastMovie(){
        return movieAPIResponse;
    }

    //---------------------
}
