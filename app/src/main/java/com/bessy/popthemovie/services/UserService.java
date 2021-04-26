package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    // GET USER BY ID
    @GET("user/id")
    Call<User> getUser(@Query("id") String email);

    // SAVE USER
    @POST("user/saveUser")
    Call<User> postUser(@Body User user);

}
