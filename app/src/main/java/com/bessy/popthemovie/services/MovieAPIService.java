package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.MovieAPIResponse;

import java.net.URL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MovieAPIService {
    @GET("/")
    Call<MovieAPIResponse> getMovie(@Query("apikey") String apikey,
                                    @Query("t") String title);

    @GET("/")
    Call<MovieAPIResponse> getMovieById(@Query("apikey") String apikey,
                                    @Query("i") String id);
}
