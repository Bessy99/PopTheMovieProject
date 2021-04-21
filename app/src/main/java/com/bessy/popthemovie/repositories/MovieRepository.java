package com.bessy.popthemovie.repositories;

import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.services.MovieAPIService;
import com.bessy.popthemovie.utils.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String TAG = "MovieRepository" ;

    private static MovieRepository instance;
    private MovieAPIService movieAPIService;

    private MovieRepository(){
        //inizializzo l'istanza di retrofit per fare le query al WebService
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.OMBD_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        movieAPIService  = retrofit.create(MovieAPIService.class);
    }

    //synchronized per far si che solo un metodo alla volta abbia accesso all'istanza
    public static synchronized MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    public void getMovie(MutableLiveData<MovieAPIResponse> movie, String title){
        Call<MovieAPIResponse> call = movieAPIService.getMovie(Constants.OMDB_API_KEY, title);
        call.enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Call<MovieAPIResponse> call, Response<MovieAPIResponse> response) {

                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                    MovieAPIResponse movieAPIResponse = new MovieAPIResponse();

                    movieAPIResponse.setGenre(response.body().getGenre());
                    Log.d(TAG, movieAPIResponse.getGenre());
                    movieAPIResponse.setImdbID(response.body().getImdbID());
                    movieAPIResponse.setPlot(response.body().getPlot());
                    movieAPIResponse.setPoster(response.body().getPoster());
                    movieAPIResponse.setTitle(response.body().getTitle());
                    movieAPIResponse.setResponse(response.isSuccessful());
                    movie.postValue(movieAPIResponse);
                }
                else if(response.errorBody() != null){
                    MovieAPIResponse movieAPIResponse = new MovieAPIResponse();
                    movieAPIResponse.setResponse(response.isSuccessful());

                    try {
                        Log.d(TAG, "errore1"+response.errorBody().string()+"- "+response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    movie.postValue(movieAPIResponse);
                }
            }

            @Override
            public void onFailure(Call<MovieAPIResponse> call, Throwable t) {
                MovieAPIResponse movieAPIResponse = new MovieAPIResponse();
                movieAPIResponse.setResponse(false);
                movie.postValue(movieAPIResponse);

                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }

}
