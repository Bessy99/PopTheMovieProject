package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.AffinitaUser;
import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAddRequest;
import com.bessy.popthemovie.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieService {
    // ADD NEW FILM
    @POST("user/addFilm")
    Call<User> addMovie(@Body MovieAddRequest movie);

    // GET LISTA AFFINITA
    @GET("film/filmMaiVisti")
    Call<List<AffinitaUser>> getListaAffinita(@Query("id") String email);


}
