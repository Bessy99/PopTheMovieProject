package com.bessy.popthemovie.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.services.UserService;
import com.bessy.popthemovie.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private static final String TAG = "UserRepository" ;

    private static UserRepository instance;
    private UserService userService;

    private UserRepository(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.DB_API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        userService  = retrofit.create(UserService.class);
    }


    public static synchronized UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    //------------------------------> SAVE USER
    public void saveUser(MutableLiveData<User> userLiveData, User userToSave){
        Call<User> call = userService.postUser(userToSave);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                    userLiveData.postValue(userToSave);
                }
                else if(response.errorBody() != null){
                   Log.d(TAG, "errore1"+response.code());
                    userLiveData.postValue(userToSave);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userLiveData.postValue(userToSave);
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }
    //------------------------------//

    //------------------------------> GET USER BY ID
    public void getUser(MutableLiveData<User> userLiveData, long id){
        Call<User> call = userService.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful() && response.body()!= null) {
                    Log.d(TAG, "risposta ok");
                    User user = new User();

                    user.setEmail(response.body().getEmail());
                    user.setId(response.body().getId());
                    user.setNome(response.body().getNome());
                    user.setCognome(response.body().getCognome());
                    user.setPassword(response.body().getPassword());
                    Gson jsonConverter = new Gson();
                    Log.d(TAG, jsonConverter.toJson(user));
                    userLiveData.postValue(user);
                }
                else if(response.errorBody() != null){
                    User user = new User();

                    Log.d(TAG, "errore1"+response.code());
                    userLiveData.postValue(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                User user = new User();
                userLiveData.postValue(user);
                Log.d(TAG, "errore2: "+t.getMessage());
            }
        });
    }
    //----------------------------------//
}
