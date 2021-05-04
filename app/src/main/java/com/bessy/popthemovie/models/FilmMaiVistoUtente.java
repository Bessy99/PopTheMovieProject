package com.bessy.popthemovie.models;

public class FilmMaiVistoUtente {
    private String email;
    private String film_id;

    public FilmMaiVistoUtente(){
        super();
    }

    public String getEmail() {
        return email;
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

}
