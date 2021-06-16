package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAddRequest;
import com.bessy.popthemovie.models.MovieRemoveRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieService {
    // ADD NEW FILM
    @POST("user/addFilm")
    Call<String> addMovie(@Body MovieAddRequest movie);

    // REMOVE FILM
    @POST("user/removeFilm")
    Call<String> removeMovie(@Body MovieRemoveRequest movie);

    //GET CLASSIFICA FILM PER AFFINITA
    @GET("film/filmInClassifica")
    Call<List<Movie>> getClassificaFilm(@Query("id") String email);

    //GET CLASSIFICA FILM OUT OF THE BOX
    @GET("film/filmInClassificaInversa")
    Call<List<Movie>> getClassificaFilmOTB(@Query("id") String email);

}
