/* Narender Latchmansing
    10073264
    Mashup: getting movie content.
    using API from http://www.omdbapi.com/ */

package com.onzin.mashup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieInfoActivity extends AppCompatActivity {


    String movieJsonString;

    Button backButton;
    ImageView posterView;
    TextView titleView;
    TextView yearView;
    TextView genreView;
    TextView directorView;
    TextView actorsView;
    TextView plotView;
    TextView ratingView;

    String title;
    String year;
    String genre;
    String director;
    String actors;
    String plot;
    String poster;
    String rating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        // collect the extras put in to this activity and instances
        Bundle extras = getIntent().getExtras();
        movieJsonString = extras.getString("result", movieJsonString);

        // assign variables to all views
        backButton = (Button) findViewById(R.id.searchButton);
        posterView = (ImageView) findViewById(R.id.posterView);
        titleView = (TextView) findViewById(R.id.titleView);
        yearView = (TextView) findViewById(R.id.yearView);
        genreView = (TextView) findViewById(R.id.genreView);
        directorView = (TextView) findViewById(R.id.directorView);
        actorsView = (TextView) findViewById(R.id.actorsView);
        plotView = (TextView) findViewById(R.id.plotView);
        ratingView = (TextView) findViewById(R.id.ratingView);



         // converting JSON string to JSON object and every child to string variables
         try {
            JSONObject movieJson = new JSONObject(movieJsonString);

            Toast.makeText(getApplicationContext(), movieJson.get("Title").toString(), Toast.LENGTH_SHORT).show();
            if (movieJson.get("response")== false){
                sendToast(movieJson.get("Error").toString());
                backToMain();
            }

            else {

                    sendToast(movieJson.get("Title").toString() + " is de titel");
                    title = movieJson.get("Title").toString();
                    year = movieJson.get("Year").toString();
                    genre = movieJson.get("Genre").toString();
                    director = movieJson.get("Director").toString();
                    actors = movieJson.get("Actors").toString();
                    plot = movieJson.get("Plot").toString();
                    poster = movieJson.get("Poster").toString();
                    rating = movieJson.get("imdbRating").toString();



                // fill all textview with the content from the JSONObject
                URI posterUrl = new URI(poster.toString());
//                posterView.setImageURI(posterUrl);
                titleView = (TextView) findViewById(R.id.titleView);
                yearView = (TextView) findViewById(R.id.yearView);
                genreView = (TextView) findViewById(R.id.genreView);
                directorView = (TextView) findViewById(R.id.directorView);
                actorsView = (TextView) findViewById(R.id.actorsView);
                plotView = (TextView) findViewById(R.id.plotView);
                ratingView = (TextView) findViewById(R.id.ratingView);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
             e.printStackTrace();
         }

        // A back button
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                backToMain();

            }

        });

    }


    // function to display a popup messgage
    public void sendToast(String message) {

        // show toast message
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    // function to go back to search page
    public void backToMain(){

        Intent mainIntent = new Intent(MovieInfoActivity.this, MainActivity.class);
        this.startActivity(mainIntent);
        finish();
    }
}
