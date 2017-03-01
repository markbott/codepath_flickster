package com.marek.codepath.flickster.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by marek on 2/27/2017.
 */

public class Movie {
    public String getPosterPath() {
        // TODO
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        // TODO
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    private String posterPath;
    private String backdropPath;
    private String originalTitle;
    private String overview;

    public Movie(JSONObject jsonMovie) throws JSONException {
        posterPath = jsonMovie.getString("poster_path");
        backdropPath = jsonMovie.getString("backdrop_path");
        originalTitle = jsonMovie.getString("original_title");
        overview = jsonMovie.getString("overview");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonMovies) {
        ArrayList<Movie> movies = new ArrayList<>(jsonMovies.length());

        for(int i = 0; i < jsonMovies.length(); i++) {
            try {
                movies.add(new Movie(jsonMovies.getJSONObject(i)));
            } catch(JSONException ex) {
                Log.e("movie-api", "error parsing movies array", ex);
            }
        }

        return movies;
    }
}
