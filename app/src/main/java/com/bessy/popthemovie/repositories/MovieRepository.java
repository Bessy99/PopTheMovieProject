package com.bessy.popthemovie.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.MovieAddRequest;
import com.bessy.popthemovie.models.MovieRemoveRequest;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.services.MovieService;
import com.bessy.popthemovie.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String TAG = "MovieRepository" ;

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
    public void saveMovie(Movie movieToSave, String email, String modalita){
        MovieAddRequest movieAddRequest = new MovieAddRequest(movieToSave,modalita,email);
        Call<User> call = movieService.addMovie(movieAddRequest);
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

    //------------------------------> REMOVE MOVIE
    // modalità: tolto dalla lista dei film da vedere oppure dalla lista dei film visti
    public void removeMovie(String movieToRemove, String email, String modalita){
        MovieRemoveRequest movieRemoveRequest = new MovieRemoveRequest(movieToRemove,modalita,email);
        Gson jsonConverter = new Gson();
        Log.d(TAG, jsonConverter.toJson(movieRemoveRequest));
        Call<User> call = movieService.removeMovie(movieRemoveRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                }
                else if(response.errorBody() != null){
                    Log.d(TAG, "errore1 "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }

    public Movie createMovie(MovieAPIResponse movieToAdd){
        return new Movie(movieToAdd.getImdbID(), movieToAdd.getTitle(), movieToAdd.getGenre(), movieToAdd.getPoster(), movieToAdd.getRuntime());
    }
    //------------------------------//

    //------------------------------> GET CLASSIFICA FILM
    public void getClassificaFilm(MutableLiveData<List<Movie>> classificaFilmLiveData, String email){
        Call<List<Movie>> call = movieService.getClassificaFilm(email);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> classificaFilm;
                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                    List<Movie> responseList = response.body();
                    classificaFilm = new ArrayList<>();
                    for(int i = 0; i<responseList.size(); i++){
                        classificaFilm.add(new Movie(responseList.get(i).getId(),
                                                     responseList.get(i).getTitolo(),
                                                     responseList.get(i).getGenere(),
                                                     responseList.get(i).getPoster(),
                                                     responseList.get(i).getDurata()));
                    }
                    Log.d(TAG, classificaFilm.get(0).getTitolo());

                    classificaFilmLiveData.postValue(classificaFilm);

                }
                else if(response.errorBody() != null){
                    Log.d(TAG, "errore1"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }
    //-------------------------------//

}
