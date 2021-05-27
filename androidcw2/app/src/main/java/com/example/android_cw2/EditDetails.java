package com.example.android_cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditDetails extends AppCompatActivity {//https://www.youtube.com/watch?v=AGdudT1iUI8&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=31&ab_channel=CodeCampSriLanka
    EditText e1, e2, e3, e4, e6;
    DBHandler db;
    String id;
    RatingBar ratingBar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {//https://www.youtube.com/watch?v=GQsibTm-aw4&t=219s&ab_channel=Stevdza-San
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        db = new DBHandler(this);
        id = getIntent().getStringExtra("id");
        Log.d("myz" , "Edit ID "+id);

        Cursor res = db.get_movie_edit(id);//get movies fro edit
        Log.d("myz" , "Edit ID "+res.getCount());
        e1 = findViewById(R.id.editT1);
        e2 = findViewById(R.id.editT2);
        e3 = findViewById(R.id.editT3);
        e4 = findViewById(R.id.editT4);
        ratingBar = findViewById(R.id.ratingBar);//https://stackoverflow.com/questions/3858600/how-to-make-ratingbar-to-show-five-stars
        e6 = findViewById(R.id.editT6);

        if( res != null && res.moveToFirst() ) {
            String movie_title = res.getString(res.getColumnIndex("title"));
            String movie_year = res.getString(res.getColumnIndex("year"));
            String movie_director = res.getString(res.getColumnIndex("director"));
            String movie_actors = res.getString(res.getColumnIndex("actors"));
            String movie_rating = res.getString(res.getColumnIndex("rating"));
            String movie_review = res.getString(res.getColumnIndex("review"));
            Log.d("myz" , "Edit ID "+movie_review);

            e1.setText(movie_title);
            e2.setText(movie_year);
            e3.setText(movie_director);
            e4.setText(movie_actors);
            ratingBar.setRating(Float.parseFloat(movie_rating));
            e6.setText(movie_review);

        }
    }

    public void edit(View view){
        boolean insert =  db.edit_movie(id,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),(int)ratingBar.getRating(),e6.getText().toString());
        Cursor movies = db.get_movies();
        Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show();
    }
}