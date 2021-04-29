package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bessy.popthemovie.databinding.ActivityMainBinding;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.bessy.popthemovie.models.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    //private FragmentLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        /*
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
         */

        View view = binding.getRoot();
        setContentView(view);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        /* recupera user da id */
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<User> user = mainActivityViewModel.getUser("c.b@gmail.com", "passwepd");
        /* */

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