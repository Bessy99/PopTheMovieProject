package com.bessy.popthemovie.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

public class MovieAPIResponse implements Parcelable {
    private boolean Response;
    private String imdbID;
    private String Title;
    private String Genre;
    //url immagine poster
    private String Poster;
    //trama film
    private String Plot;
    //durata film
    private String Runtime;
    //per permettere la mappatura dei campi in entrata
    private String imdbRating;

    private float rating;



    public MovieAPIResponse(){}

    public MovieAPIResponse(boolean response, String imdbID, String title, String genre, String poster, String plot, String runtime, float imdbRating) {
        Response = response;
        this.imdbID = imdbID;
        Title = title;
        Genre = genre;
        Poster = poster;
        Plot = plot;
        Runtime = runtime;
        this.rating = imdbRating;
    }

    public MovieAPIResponse(boolean response, String imdbID, String title, String genre, String poster, String plot, String runtime, String imdbRating) {
        Response = response;
        this.imdbID = imdbID;
        Title = title;
        Genre = genre;
        Poster = poster;
        Plot = plot;
        Runtime = runtime;
        try{
            this.rating = Float.parseFloat(imdbRating);
        }
        catch(NumberFormatException e){
            this.rating = 0;
        }
    }

    protected MovieAPIResponse(Parcel in) {
        Response = in.readByte() != 0;
        imdbID = in.readString();
        Title = in.readString();
        Genre = in.readString();
        Poster = in.readString();
        Plot = in.readString();
        Runtime = in.readString();
        try{
            rating = Float.parseFloat(in.readString());
        }
        catch(NumberFormatException e){
            rating = 0;
        }
    }

    public static final Creator<MovieAPIResponse> CREATOR = new Creator<MovieAPIResponse>() {
        @Override
        public MovieAPIResponse createFromParcel(Parcel in) {
            return new MovieAPIResponse(in);
        }

        @Override
        public MovieAPIResponse[] newArray(int size) {
            return new MovieAPIResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (Response ? 1 : 0));
        dest.writeString(imdbID);
        dest.writeString(Title);
        dest.writeString(Genre);
        dest.writeString(Poster);
        dest.writeString(Plot);
        dest.writeString(Runtime);
        dest.writeFloat(rating);
    }

    //------------------------------> GETTER E SETTER
    public boolean isResponse() {
        return Response;
    }

    public void setResponse(boolean response) {
        Response = response;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float imdbRatingFloat) {
        this.rating = imdbRatingFloat /2;
    }

    public void setRating(String imdbRating) {
        try{
            setRating(Float.parseFloat(imdbRating));
        }
        catch(Exception e){
            this.rating = 0;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return "MovieAPIResponse{" +
                "Response=" + Response +
                ", imdbID='" + imdbID + '\'' +
                ", Title='" + Title + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", imdbRating='" + rating + '\'' +
                ", Plot='" + Plot + '\'' +
                '}';
    }

    public String getImdbRatingString() {
        return imdbRating;
    }


    //-------------------------------------//


}
