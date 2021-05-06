package com.bessy.popthemovie.models;

public class FilmMaiVistoUtente {
    private String email = "nessuna email";
    private String film_id = "nessun film";

    public FilmMaiVistoUtente(){
        super();
    }

    public FilmMaiVistoUtente(String email, String film_id){

        super();
        this.email = email;
        this.film_id = film_id;
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
