package com.bessy.popthemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.bessy.popthemovie.databinding.ActivityMainBinding;
import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;
import com.bessy.popthemovie.models.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //toolbar
        setSupportActionBar(binding.toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigationlisteUtente, R.id.navigationSearch, R.id.navigationSimilar, R.id.navigationOutOfTheBox).build();

        //Bottom navigation
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Constants.SHARED_PREFERENCES_USERNAME,null);
        String password = sharedPreferences.getString(Constants.SHARED_PREFERENCES_PASSWORD, null);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<User> user = mainActivityViewModel.getUser(username, password);



        /* recupera user da id
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        MutableLiveData<User> user = mainActivityViewModel.getUser("c.besana@gmail.com", "password1");
        //MutableLiveData<User> user = mainActivityViewModel.getUser("c.n@hmfg.com", "fhhd");
         */

        /* registra nuovo user
        User u = new User("pop@gmail.com","passd5","nompo","cognop", null, null);
        user = mainActivityViewModel.saveUser(u);
        */

        /* recupera movie con titolo "hunger"
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        MutableLiveData<MovieAPIResponse> movieLiveData = mainActivityViewModel.getMovieByTitle("lost");
        */
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}