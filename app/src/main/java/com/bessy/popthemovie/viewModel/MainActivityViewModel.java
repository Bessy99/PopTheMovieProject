package com.bessy.popthemovie.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bessy.popthemovie.models.Movie;
import com.bessy.popthemovie.models.MovieAPIResponse;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.repositories.MovieAPIRepository;
import com.bessy.popthemovie.repositories.MovieRepository;
import com.bessy.popthemovie.repositories.UserRepository;

import java.util.Iterator;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<User> user;
    private MutableLiveData<MovieAPIResponse> movieAPIResponse;
    private MutableLiveData<List<Movie>> classificaFilm;
    private MovieRepository movieRepository = MovieRepository.getInstance();

    //------------------> User

    public MutableLiveData<User> getUser(String email, String password){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        UserRepository.getInstance().getUser(user, email, password);
        return user;
    }

    public MutableLiveData<User> getUser(){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        return user;
    }

    public List<Movie> getFilmDaVedere(){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        return user.getValue().getFilmDaVedere();
    }

    public List<Movie> getFilmVisti(){
        if(user == null){
            user = new MutableLiveData<User>();
        }
        return user.getValue().getFilmVisti();
    }

    public MutableLiveData<User> saveUser(User userToSave){
        UserRepository.getInstance().saveUser(user, userToSave);
        return user;
    }
    //--------------------

    //--------------------> Movie API Response

    public MutableLiveData<MovieAPIResponse> getMovieByTitle(String title){
        if(movieAPIResponse == null){
            movieAPIResponse = new MutableLiveData<MovieAPIResponse>();
        }
        MovieAPIRepository.getInstance().getMovie(movieAPIResponse, title);
        return movieAPIResponse;
    }

    public MutableLiveData<MovieAPIResponse> getMovieById(String id){
        if(movieAPIResponse == null){
            movieAPIResponse = new MutableLiveData<MovieAPIResponse>();
        }
        MovieAPIRepository.getInstance().getMovieById(movieAPIResponse, id);
        return movieAPIResponse;
    }

    public MutableLiveData<MovieAPIResponse> getLastMovie(){
        if(movieAPIResponse == null){
            movieAPIResponse = new MutableLiveData<MovieAPIResponse>();
        }
        return movieAPIResponse;
    }

    //---------------------//

    //--------------------> Classifica film

    public MutableLiveData<List<Movie>> getClassificaFilm(){
        if(classificaFilm == null){
            classificaFilm = new MutableLiveData<List<Movie>>();
            if(getUser() != null && getUser().getValue()!=null) {
                MovieRepository.getInstance().getClassificaFilm(classificaFilm, getUser().getValue().getEmail());
            }
            else Log.d("mainViewModel", "user vuoto");
        }
        return classificaFilm;
    }

    //--------------------//

    //---------------------> Aggiungere movie alle liste dello User

    public void addFilmVisto(MovieAPIResponse movieToAdd){
        Movie movie = movieRepository.createMovie(movieToAdd);
        movieRepository.saveMovie(movie, getUser().getValue().getEmail(),"visti");
        getUser().getValue().getFilmVisti().add(movie);
    }

    public void addFilmDaVedere(MovieAPIResponse movieToAdd){
        Movie movie = movieRepository.createMovie(movieToAdd);
        movieRepository.saveMovie(movie, getUser().getValue().getEmail(),"daVedere");
        getUser().getValue().getFilmDaVedere().add(movie);
    }

    public void addFilmVisto(Movie movie){
        movieRepository.saveMovie(movie, getUser().getValue().getEmail(),"visti");
        getUser().getValue().getFilmVisti().add(movie);
    }

    public void addFilmDaVedere(Movie movie){
        movieRepository.saveMovie(movie, getUser().getValue().getEmail(),"daVedere");
        getUser().getValue().getFilmDaVedere().add(movie);
    }


    //----------------------//
    //---------------------> Rimuovere movie dalle liste dello User

    public void removeFilm(String movieToRemove){
        Log.d("ViewModel", movieToRemove);
        Iterator<Movie> i = getUser().getValue().getFilmVisti().iterator();
        boolean flag = true;
        while(i.hasNext() && flag){
            Movie m = i.next();
            if(m.getId().equals(movieToRemove)){
                movieRepository.removeMovie(movieToRemove, getUser().getValue().getEmail(),"visti");
                flag = false;
                getUser().getValue().getFilmVisti().remove(m);
            }
        }
        i = getUser().getValue().getFilmDaVedere().iterator();
        while(i.hasNext() && flag){
            Movie m = i.next();
            if(m.getId().equals(movieToRemove)){
                movieRepository.removeMovie(movieToRemove, getUser().getValue().getEmail(),"daVedere");
                flag = false;
                getUser().getValue().getFilmDaVedere().remove(m);
            }
        }
    }

    //---------------------//


}
