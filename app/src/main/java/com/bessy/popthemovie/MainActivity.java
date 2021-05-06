package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.bessy.popthemovie.databinding.ActivityMainBinding;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.bessy.popthemovie.models.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Bottom navigation
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        /* recupera user da id */
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //MutableLiveData<User> user = mainActivityViewModel.getUser("pop@gmail.com", "passd5");
        MutableLiveData<User> user = mainActivityViewModel.getUser("c.n@hmfg.com", "fhhd");
        /* */

        /* registra nuovo user
        User u = new User("pop@gmail.com","passd5","nompo","cognop", null, null);
        user = mainActivityViewModel.saveUser(u);
        */

        /* recupera movie con titolo "hunger"
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        MutableLiveData<MovieAPIResponse> movieLiveData = mainActivityViewModel.getMovieByTitle("lost");
        */
    }

}