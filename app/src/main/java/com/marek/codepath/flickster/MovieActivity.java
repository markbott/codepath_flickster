package com.marek.codepath.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.marek.codepath.flickster.adapters.MovieArrayAdapter;
import com.marek.codepath.flickster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;
import static com.marek.codepath.flickster.models.Movie.fromJSONArray;

public class MovieActivity extends AppCompatActivity {
    List<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("movie-api", "success");
                try {
                    JSONArray jsonMovies = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(jsonMovies));
                    movieAdapter.notifyDataSetChanged();
                } catch(JSONException ex) {
                    Log.w("movie-api", "error retrieving movies from response", ex);
                }
                //super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("movie-api", "failure", throwable);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
