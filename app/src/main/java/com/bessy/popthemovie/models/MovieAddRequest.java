package com.bessy.popthemovie.models;

public class MovieAddRequest {
    Movie film;
    String modalita;
    String email;

    public MovieAddRequest(Movie film, String modalita, String email) {
        this.film = film;
        this.modalita = modalita;
        this.email = email;
    }

    public Movie getFilm() {
        return film;
    }

    public void setFilm(Movie film) {
        this.film = film;
    }

    public String getModalita() {
        return modalita;
    }

    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
