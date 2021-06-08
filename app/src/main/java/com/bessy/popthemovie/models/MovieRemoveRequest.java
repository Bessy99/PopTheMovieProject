package com.bessy.popthemovie.models;

public class MovieRemoveRequest {
    String filmId;
    String modalita;
    String email;

    public MovieRemoveRequest(String film, String modalita, String email) {
        this.filmId = film;
        this.modalita = modalita;
        this.email = email;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
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
