package com.bessy.popthemovie.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String id;
    private String titolo;
    private String genere;
    private String poster;
    private String durata;

    public Movie(){}

    public Movie(String id, String titolo, String genere, String poster, String durata) {
        this.id = id;
        this.titolo = titolo;
        this.genere = genere;
        this.poster = poster;
        this.durata = durata;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        titolo = in.readString();
        genere = in.readString();
        poster = in.readString();
        durata = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titolo);
        dest.writeString(genere);
        dest.writeString(durata);
        dest.writeString(poster);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

}
