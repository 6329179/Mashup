/* Narender Latchmansing
    10073264
    Mashup: getting movie content.
    using API from http://www.omdbapi.com/ */

package com.onzin.mashup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText titleEdit;
    EditText yearEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (Button) findViewById(R.id.searchButton);
        titleEdit = (EditText) findViewById(R.id.titleEdit);
        yearEdit = (EditText) findViewById(R.id.yearEdit);


        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // use params : params[0] (array) als je wilt invoeren, nu is site in verwerkt
                String titleInput = titleEdit.getText().toString();
                String yearInput = yearEdit.getText().toString();

                TagAsyncTask getJsonTask = new TagAsyncTask(MainActivity.this);
                getJsonTask.execute(titleInput, yearInput);
//                new TagAsyncTask(MainActivity.this).execute(titleInput,yearInput);

//                new TagAsyncTask(getApplicationContext()).execute(titleInput,yearInput);
//                new TagAsyncTask(MainActivity.this).execute();

            }

        });


    }

    // function to display a popup messgage
    public void sendToast(String message) {

        // show toast message
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
