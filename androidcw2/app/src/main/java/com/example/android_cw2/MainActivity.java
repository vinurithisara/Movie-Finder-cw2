package com.example.android_cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchRegisterMovie(View view) {//to launch register movie activity
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, RegisterMovie.class);
        startActivity(intent);
    }

    public void launchDisplayMovies(View view) {//to launch display movie activity
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, DisplayMovies.class);
        startActivity(intent);
    }
    public void launchFavourites(View view) {//to launch favourite activity
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    public void launchEditMovies(View view) {//to launch edit movie activity
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, EditMovies.class);
        startActivity(intent);
    }

    public void launchSearch(View view) {//to launch search activity
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
    public void launchRatings(View view) {//to launch rating activity
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, Activity6.class);
        startActivity(intent);
    }



}