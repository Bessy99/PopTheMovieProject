package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieService {
    // SAVE USER
    @POST("film/addFilm")
    Call<User> addMovie(@Body Movie movie,
                        @Body User user,
                        @Query("modalita") String modalita
                    );
}
