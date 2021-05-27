package com.example.android_cw2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "movies_db";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }//https://www.youtube.com/watch?v=76tm4INTJZo&ab_channel=IndraSubediIndraSubedi
    //https://www.youtube.com/watch?v=9t8VVWebRFM&ab_channel=AllCodingTutorials

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists movies" + " (movie_id INTEGER PRIMARY KEY AUTOINCREMENT,title text, year text,director text,actors text,rating INTEGER,review text,favourite int default 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }

    //to add data of movies
    public boolean insert_movies(String title, String year, String director, String actors, int rating, String review) {

        SQLiteDatabase db = this.getWritableDatabase();

        String insert_movie = "INSERT INTO movies (title, year, director,actors,rating,review) VALUES (\"" + title + "\",\"" + year + "\",\"" + director + "\",\"" + actors + "\",\"" + rating + "\",\"" + review + "\")";
        db.execSQL(insert_movie);
        return true;
    }
    //edit movie
    public boolean edit_movie(String id, String title, String year, String director, String actors, int rating, String review) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE movies SET title = \""+title+"\",  year = \""+year+"\",  director = \""+director+"\",  actors = \""+actors+"\",  rating = \""+rating+"\",  review = \""+review+"\" where movie_id = " + Integer.parseInt(id));

        return true;
    }
    //get all movies in the list
    public Cursor get_movies(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies ORDER BY title ASC",null);
        return res;
    }
    //get all favourite movies in the list
    public Cursor get_fav_movies(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where favourite= "+1,null);
        return res;
    }


    //get specific movie in the list
    public Cursor get_movie_edit(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where movie_id = "+ Integer.parseInt(id),null);
        return res;
    }

    public void set_fav(int[] movie_fav,String[] movie_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        for(int a=0;a<movie_fav.length;a++) {
            try {
                if(movie_fav[a] == 0) {
                    db.execSQL("UPDATE movies SET favourite = 0 where movie_id = " + Integer.parseInt(movie_id[a]));
                }else{
                    db.execSQL("UPDATE movies SET favourite = 1 where movie_id = " + Integer.parseInt(movie_id[a]));
                }
            } catch (SQLException e) {
                Log.d("aa", "Something went wrong");
            }
        }
    }

    //search movie from a word
    public Cursor search_movies(String word) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where title like '%"+word+"%' OR title like '"+word+"%' OR title like '%"+word+"' OR title like '"+word+"'" +
                " OR director like '%"+word+"%' OR director like '"+word+"%' OR director like '%"+word+"' OR director like '"+word+"'"+
                "OR actors like '%"+word+"%' OR director like '"+word+"%' OR director like '%"+word+"' OR director like '"+word+"'",null);

        return res;
    }
}
