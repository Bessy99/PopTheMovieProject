package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAddRequest;
import com.bessy.popthemovie.models.MovieRemoveRequest;
import com.bessy.popthemovie.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieService {
    // ADD NEW FILM
    @POST("user/addFilm")
    Call<User> addMovie(@Body MovieAddRequest movie);

    // REMOVE FILM
    @POST("user/removeFilm")
    Call<User> removeMovie(@Body MovieRemoveRequest movie);


    //GET CLASSIFICA FILM PER AFFINITA
    @GET("film/filmInClassifica")
    Call<List<Movie>> getClassificaFilm(@Query("id") String email);
}
