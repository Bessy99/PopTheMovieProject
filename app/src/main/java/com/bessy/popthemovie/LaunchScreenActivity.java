package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.LaunchScreenActivityViewModel;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;


/**
 * Launch Screen Activity that can be used to show a splash screen and
 * to choose which Activity to start based on the fact that user
 * already set his/her preferences or not.
 */
public class LaunchScreenActivity extends AppCompatActivity {
    private LaunchScreenActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LaunchScreenActivityViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        String username = sharedPref.getString(Constants.SHARED_PREFERENCES_USERNAME, null);
        String password = sharedPref.getString(Constants.SHARED_PREFERENCES_PASSWORD, null);
        if (username != null && password != null) {
            MutableLiveData<Boolean> userExists = viewModel.userExist(username,password);
            Observer<Boolean> observerUserExists = new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean exists) {
                    chooseActivityToLaunch(exists);
                }
            };
            viewModel.exists().observe(this, observerUserExists);
        } else {
            chooseActivityToLaunch(false);
        }

        // It removes this Activity from the stack
        finish();

    }

    private void chooseActivityToLaunch(boolean exists) {
        Intent intent;
        if(exists){
            intent = new Intent(this, MainActivity.class);}
        else{
            intent = new Intent(this, LoginActivity.class);}
        startActivity(intent);
    }
}
