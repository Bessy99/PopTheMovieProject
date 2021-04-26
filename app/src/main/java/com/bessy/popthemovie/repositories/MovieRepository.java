package com.bessy.popthemovie.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.services.MovieService;
import com.bessy.popthemovie.services.UserService;
import com.bessy.popthemovie.utils.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String TAG = "UserRepository" ;

    private static MovieRepository instance;
    private MovieService movieService;

    private MovieRepository(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.DB_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        movieService = retrofit.create(MovieService.class);
    }


    public static synchronized MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    //------------------------------> SAVE MOVIE
    // modalità: aggiungerlo alla lista dei film da vedere oppure alla lista dei film visti
    public void saveMovie(Movie movieToSave, User userAttuale, String modalita){
        Call<User> call = movieService.addMovie(movieToSave, userAttuale, modalita);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                }
                else if(response.errorBody() != null){
                    Log.d(TAG, "errore1"+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }
    //------------------------------//
}
