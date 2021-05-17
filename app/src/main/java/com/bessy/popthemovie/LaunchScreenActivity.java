package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bessy.popthemovie.utils.Constants;

import java.util.Set;

/**
 * Launch Screen Activity that can be used to show a splash screen and
 * to choose which Activity to start based on the fact that user
 * already set his/her preferences or not.
 */
public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseActivityToLaunch();

        // It removes this Activity from the stack
        finish();
    }

    private void chooseActivityToLaunch() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        String username = sharedPref.getString(Constants.SHARED_PREFERENCES_USERNAME, null);
        String password = sharedPref.getString(Constants.SHARED_PREFERENCES_PASSWORD, null);

        Intent intent;
        if (username != null && password != null) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
    }
}
