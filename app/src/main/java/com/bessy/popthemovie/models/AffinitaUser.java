package com.bessy.popthemovie.models;

public class AffinitaUser {
    private int numFilmInComune;
    private String email;

    public AffinitaUser() {
        super();
    }

    public int getNumFilmInComune() {
        return numFilmInComune;
    }
    public String getEmail() {
        return email;
    }
    public void setNumFilmInComune(int numFilmInComune) {
        this.numFilmInComune = numFilmInComune;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
