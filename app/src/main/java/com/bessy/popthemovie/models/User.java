package com.bessy.popthemovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;

public class User implements Parcelable {
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Set<Movie> filmVisti;
    private Set<Movie> filmDaVedere;

    public User(){}

    public User(String email, String password, String nome, String cognome, Set<Movie> filmVisti, Set<Movie> filmDaVedere) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.filmVisti = filmVisti;
        this.filmDaVedere = filmDaVedere;
    }

    protected User(Parcel in) {
        email = in.readString();
        password = in.readString();
        nome = in.readString();
        cognome = in.readString();
        filmVisti = (Set<Movie>) in.readValue(Set.class.getClassLoader());
        filmDaVedere = (Set<Movie>) in.readValue(Set.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(nome);
        dest.writeString(cognome);
        dest.writeValue(filmVisti);
        dest.writeValue(filmDaVedere);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Set<Movie> getFilmVisti() {
        return filmVisti;
    }

    public void setFilmVisti(Set<Movie> filmVisti) {
        this.filmVisti = filmVisti;
    }

    public Set<Movie> getFilmDaVedere() {
        return filmDaVedere;
    }

    public void setFilmDaVedere(Set<Movie> filmDaVedere) {
        this.filmDaVedere = filmDaVedere;
    }
    //--------------------------------------------
}
