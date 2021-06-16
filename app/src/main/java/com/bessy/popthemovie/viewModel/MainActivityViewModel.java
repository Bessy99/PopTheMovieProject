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
    private MutableLiveData<List<Movie>> classificaFilmOTB;
    private MovieRepository movieRepository = MovieRepository.getInstance();
    private int positionSimilar = 0;
    private int positionOTB = 0;

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

    //--------------------> Classifiche film

    public MutableLiveData<List<Movie>> getClassificaFilmSimilar(){
        if(classificaFilm == null){
            classificaFilm = new MutableLiveData<List<Movie>>();
            if(getUser() != null && getUser().getValue()!=null) {
                String s = getUser().getValue().getEmail();
                Log.d("mainViewModelGet", s);
                MovieRepository.getInstance().getClassificaFilm(classificaFilm,s);
            }
            else Log.d("mainViewModel", "user vuoto");
        }
        return classificaFilm;
    }
    public MutableLiveData<List<Movie>> getClassificaFilmOTB(){
        if(classificaFilmOTB == null){
            classificaFilmOTB = new MutableLiveData<List<Movie>>();
            if(getUser() != null && getUser().getValue()!=null) {
                String s = getUser().getValue().getEmail();
                MovieRepository.getInstance().getClassificaFilmOTB(classificaFilmOTB,s);
            }
            else Log.d("mainViewModel", "user vuoto");
        }
        return classificaFilmOTB;
    }

    public void aggiornaClassificaFilm(){
        if(classificaFilm == null) {
            classificaFilm = new MutableLiveData<List<Movie>>();
        }
        if(classificaFilmOTB == null) {
            classificaFilmOTB = new MutableLiveData<List<Movie>>();
        }
        if(getUser() != null && getUser().getValue()!=null) {
            String s = getUser().getValue().getEmail();
            Log.d("mainViewModelAggiorna", s);
            MovieRepository.getInstance().getClassificaFilm(classificaFilm, s);
            MovieRepository.getInstance().getClassificaFilmOTB(classificaFilmOTB, s);
        }
        else Log.d("mainViewModel", "user vuoto");
    }

    //--------------------//

    //---------------------> Aggiungere movie alle liste dello User

    public boolean addFilmVisto(){
        Movie movie = movieRepository.createMovie(getLastMovie().getValue());
        return addFilmVisto(movie);
    }

    public boolean addFilmDaVedere(){
        Movie movie = movieRepository.createMovie(getLastMovie().getValue());
        return addFilmDaVedere(movie);
    }

    public boolean addFilmVisto(Movie movie){
        if(controllo(movie.getId())) {
            movieRepository.saveMovie(movie, getUser().getValue().getEmail(), "visti", this);
            getUser().getValue().getFilmVisti().add(movie);
            return true;
        }
        else return false;
    }

    public boolean addFilmDaVedere(Movie movie){
        if(controllo(movie.getId())) {
            movieRepository.saveMovie(movie, getUser().getValue().getEmail(), "daVedere", this);
            getUser().getValue().getFilmDaVedere().add(movie);
            return true;
        }
        else return false;
    }

    private boolean controllo(String imdbId) {
        List<Movie> filmVisti = getFilmVisti();
        List<Movie> filmDaVedere = getFilmDaVedere();
        boolean nonPresente = true;
        Iterator<Movie> iVisti = filmVisti.iterator();
        Iterator<Movie> iDaVedere = filmDaVedere.iterator();
        while (nonPresente && iVisti.hasNext()) {
            Movie m = iVisti.next();
            if (m != null) {
                if (imdbId.equals(m.getId())) {
                    nonPresente = false;
                }
            }
        }
        while (nonPresente && iDaVedere.hasNext()) {
            Movie m = iDaVedere.next();
            if (m != null) {
                if (imdbId.equals(m.getId())) {
                    nonPresente = false;
                }
            }
        }
        return nonPresente;
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
                movieRepository.removeMovie(movieToRemove, getUser().getValue().getEmail(),"visti", this);
                flag = false;
                getUser().getValue().getFilmVisti().remove(m);

            }
        }
        i = getUser().getValue().getFilmDaVedere().iterator();
        while(i.hasNext() && flag){
            Movie m = i.next();
            if(m.getId().equals(movieToRemove)){
                movieRepository.removeMovie(movieToRemove, getUser().getValue().getEmail(),"daVedere", this);
                flag = false;
                getUser().getValue().getFilmDaVedere().remove(m);
            }
        }
    }

    //---------------------//

    //---------------------> Salva la posizione a cui è arrivata la lista
    public int getPositionSimilar(){
        return positionSimilar;
    }
    public void setPositionSimilar(int position){
        positionSimilar = position;
    }

    public int getPositionOTB(){
        return positionOTB;
    }
    public void setPositionOTB(int position){
        positionOTB = position;
    }
    //--------------------//

}
