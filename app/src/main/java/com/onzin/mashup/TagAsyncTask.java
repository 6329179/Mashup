/* Narender Latchmansing
    10073264
    Mashup: getting movie content.
    using API from http://www.omdbapi.com/ */

package com.onzin.mashup;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


// AsyncTask<params, progress, result> params of string
public class TagAsyncTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private MainActivity activity;

//    //constructor
//    public TagAsyncTask(Context context){
//        super();
//        this.activity = activity;
////        this.context = this.activity.getApplicationContext();
//        this.context = context;
//
//    }

    // constructor
    // linking MainActivity and his context to this class
    public TagAsyncTask(MainActivity activity){
        super();
        this.activity = activity;
        this.context = this.activity.getApplicationContext();


    }



    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        // show user you'll fetch data
        sendToast("Getting Data from server");
    }

    @Override
    protected String doInBackground(String... params) {

        //get data from server
//        String site = "http://www.omdbapi.com/?t=ice+cube&y=&plot=short&r=json";
//        String site = "http://www.omdbapi.com/?" + "t="+params[0]+"&y="+params[1]+"&plot=short&r=json";


        URL url = null;
        try{
            String site = "http://www.omdbapi.com/";
            url = new URL(site);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make connection
        HttpURLConnection connection;
        if (url != null){
            try {

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("t", params[0]);
                connection.setRequestProperty("y",params[1]);
                connection.setRequestProperty("plot","short");
                connection.setRequestProperty("r", "json");


                // read the responsecode
                Integer response = connection.getResponseCode();

                if (200 <= response && response <= 299){
                    // read in data
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    String returnValue = "";
                    while ((line = br.readLine()) != null){
                        returnValue = returnValue + line;
                    }

                    return returnValue;
                }
                else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    // TODO communicate error code

                }

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        //update if necessary
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        Toast.makeText(context,result, Toast.LENGTH_SHORT).show();

        Toast.makeText(context,"some data was found", Toast.LENGTH_SHORT).show();
        //alert user nothing was found

        if (result.length() == 0){
            Toast.makeText(context,"No data was found", Toast.LENGTH_SHORT).show();
        }
        else {

            sendData(result.toString());

        }
    }

    // when data fetched from http request, send string JSON object to next activity
    public void sendData(String result) {

        Intent movieInfoIntent = new Intent(this.activity, MovieInfoActivity.class);
        movieInfoIntent.putExtra("result", result);
        this.context.startActivity(movieInfoIntent);
        activity.finish();

    }

    // function to display a popup messgage
    public void sendToast(String message) {

        // show toast message
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



}
