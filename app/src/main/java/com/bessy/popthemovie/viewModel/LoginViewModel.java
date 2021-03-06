package com.bessy.popthemovie.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.repositories.UserRepository;

public class LoginViewModel extends ViewModel {
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
        if(userExists == null){
            userExists = new MutableLiveData<Boolean>();
        }
        return userExists;
    }
}
