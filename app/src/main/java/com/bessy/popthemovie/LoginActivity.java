package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bessy.popthemovie.utils.Constants;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(Constants.SHARED_PREFERENCES_USERNAME, "c.besana@gmail.com");
        editor.putString(Constants.SHARED_PREFERENCES_PASSWORD, "password1");

        editor.apply();


    }
}