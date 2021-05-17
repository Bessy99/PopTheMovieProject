package com.bessy.popthemovie.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.repositories.MovieAPIRepository;
import com.bessy.popthemovie.repositories.MovieRepository;
import com.bessy.popthemovie.repositories.UserRepository;

import java.util.List;

public class LaunchScreenActivityViewModel extends ViewModel {
    private MutableLiveData<Boolean> userExists;

    //------------------> User

    public MutableLiveData<Boolean> userExist(String email, String password){
        if(userExists == null){
            userExists = new MutableLiveData<Boolean>();
        }
        UserRepository.getInstance().userExists(userExists, email, password);
        return userExists;
    }

   //--------------------//

    public MutableLiveData<Boolean> exists(){
        return userExists;
    }
}
