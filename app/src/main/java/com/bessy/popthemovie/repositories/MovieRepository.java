package com.bessy.popthemovie.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bessy.popthemovie.models.AffinitaUser;
import com.bessy.popthemovie.models.FilmMaiVistoUtente;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.MovieAddRequest;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.services.MovieService;
import com.bessy.popthemovie.services.UserService;
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

    public Movie createMovie(MovieAPIResponse movieToAdd){
        return new Movie(movieToAdd.getImdbID(), movieToAdd.getTitle(), movieToAdd.getGenre(), movieToAdd.getPoster());
    }
    //------------------------------//
    //------------------------------> GET LISTA AFFINITA USER
    public void getListaAffinita(MutableLiveData<List<AffinitaUser>> affinitaUserLiveData, String email){
        Call<List<AffinitaUser>> call = movieService.getListaAffinita(email);
        call.enqueue(new Callback<List<AffinitaUser>>() {
            @Override
            public void onResponse(Call<List<AffinitaUser>> call, Response<List<AffinitaUser>> response) {
                List<AffinitaUser> listaAffinita;
                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                    List<AffinitaUser> responseList = response.body();
                    listaAffinita = new ArrayList<>();
                    for(int i = 0; i<responseList.size(); i++){
                        AffinitaUser affinitaUser = new AffinitaUser();
                        affinitaUser.setEmail(responseList.get(i).getEmail());
                        affinitaUser.setNumFilmInComune(responseList.get(i).getNumFilmInComune());
                        listaAffinita.add(affinitaUser);
                    }
                    affinitaUserLiveData.postValue(listaAffinita);

                }
                else if(response.errorBody() != null){
                    Log.d(TAG, "errore1"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<AffinitaUser>> call, Throwable t) {
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }
    //-------------------------------//
    //------------------------------> GET FILM MAI VISTO
    public void getFilmMaiVisto(MutableLiveData<List<FilmMaiVistoUtente>> filmMaiVistiLiveData, String email){
        Call<List<FilmMaiVistoUtente>> call = movieService.getFilmMaiVisti(email);
        call.enqueue(new Callback<List<FilmMaiVistoUtente>>() {
            @Override
            public void onResponse(Call<List<FilmMaiVistoUtente>> call, Response<List<FilmMaiVistoUtente>> response) {
                List<FilmMaiVistoUtente> listaFilmMaiVisti;
                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                    List<FilmMaiVistoUtente> responseList = response.body();
                    listaFilmMaiVisti = new ArrayList<>();
                    for(int i = 0; i<responseList.size(); i++){
                        listaFilmMaiVisti.add(new FilmMaiVistoUtente(responseList.get(i).getEmail(),responseList.get(i).getFilm_id()));
                    }
                    Log.d(TAG, listaFilmMaiVisti.get(0).getEmail());

                    filmMaiVistiLiveData.postValue(listaFilmMaiVisti);

                }
                else if(response.errorBody() != null){
                    Log.d(TAG, "errore1"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<FilmMaiVistoUtente>> call, Throwable t) {
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }
    //-------------------------------//

}
