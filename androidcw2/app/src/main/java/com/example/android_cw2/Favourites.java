package com.example.android_cw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Favourites extends AppCompatActivity {

    DBHandler db;
    String[] title,id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    RecyclerView.LayoutManager movielistlayout;//https://www.youtube.com/watch?v=0XUhYLdcG1E&ab_channel=WamMolakarayo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);



        no_movies = findViewById(R.id.no_movies1);
        recyclerView =  findViewById(R.id.recyclr_view1);
        db = new DBHandler(this);
        int count1 = 0;
        Cursor res = db.get_fav_movies();//call get_fav_movies from DBHandler

        Log.d("myz", "Number of movies  "+res.getCount());
        if(res.getCount() != 0) {
            title = new String[res.getCount()];
            id = new String[res.getCount()];
            movies_fav = new int[res.getCount()];

            while (res.moveToNext()) {
                title[count1] = res.getString(res.getColumnIndex("title"));
                id[count1] = res.getString(res.getColumnIndex("movie_id"));
                movies_fav[count1] = res.getInt(res.getColumnIndex("favourite"));
                count1++;
            }
            recycler();
        }else{
            Log.d("myz", "No Movies");
            recyclerView.setVisibility(View.GONE);
            no_movies.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("WrongConstant")
    public void recycler() {
        movielistlayout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(movielistlayout);
        adapter = new MovieAdapter(this,title,id,movies_fav);
        recyclerView.setAdapter(adapter);
    }
    public void addToFavouriteFromFavourites(View view){
        db.set_fav(adapter.movie_fav, id);
        Toast.makeText(Favourites.this,"Movies Added to Favourite",Toast.LENGTH_SHORT).show();
    }
    }
