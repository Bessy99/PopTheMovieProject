package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bessy.popthemovie.databinding.ActivityMainBinding;
import com.bessy.popthemovie.models.MainActivityViewModel;
import com.bessy.popthemovie.models.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* recupera user da id
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<User> user = mainActivityViewModel.getUser("c.b@gmail.com");
         */

        /* registra nuovo user
        User u = new User("prova@gmail.com","password5","nomeP","cognomep");
        user = mainActivityViewModel.saveUser(u);
         */

        /* recupera movie con titolo "hunger"
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        MutableLiveData<MovieAPIResponse> movieLiveData = movieViewModel.getMovie("hunger");
        */


    }
}