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

public class DisplayMovies extends AppCompatActivity {

    DBHandler db;
    String[] title,id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    RecyclerView.LayoutManager movielistlayout;//https://www.youtube.com/watch?v=0XUhYLdcG1E&ab_channel=WamMolakarayo
    //https://www.youtube.com/watch?v=BefSjV_FPCM&ab_channel=ThemedzDz
//https://developer.android.com/guide/topics/ui/layout/recyclerview
    //https://www.javatpoint.com/android-recyclerview-list-example
    @Override
    protected void onCreate(Bundle savedInstanceState) {//https://www.youtube.com/watch?v=gKPpePHlpMU&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=28&ab_channel=CodeCampSriLanka
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        no_movies = findViewById(R.id.no_movies);//if list has no movies
        recyclerView =  findViewById(R.id.recyclr_view);
        db = new DBHandler(this);
        int count1 = 0;
        Cursor res = db.get_movies();

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

    public void addToFavourite(View view){//to add favourite list
        db.set_fav(adapter.movie_fav, id);
        Toast.makeText(DisplayMovies.this,"Movies Added to Favourite",Toast.LENGTH_SHORT).show();

    }

}