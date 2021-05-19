package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.LoginViewModel;

public class LaunchScreenActivity extends AppCompatActivity {
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        String username = sharedPref.getString(Constants.SHARED_PREFERENCES_USERNAME, null);
        String password = sharedPref.getString(Constants.SHARED_PREFERENCES_PASSWORD, null);
        if (username != null && password != null) {
            viewModel.userExist(username,password);
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
