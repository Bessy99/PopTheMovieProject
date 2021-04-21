package com.bessy.popthemovie.services;

import com.bessy.popthemovie.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("user/id")
    Call<User> getUser(@Query("id") long id);
}
