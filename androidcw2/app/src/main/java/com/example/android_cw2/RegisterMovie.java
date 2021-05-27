package com.example.android_cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {//https://www.youtube.com/watch?v=mEuGvreqFZM&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=26&ab_channel=CodeCampSriLanka
//https://www.youtube.com/watch?v=3V1UsSdq82k&ab_channel=ARSLTech
    EditText Text1, Text2, Text3,Text4, Text5, Text6;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        db = new DBHandler(this);

        Text1 = findViewById(R.id.editT1);
        Text2 = findViewById(R.id.editT2);
        Text3 = findViewById(R.id.editT3);
        Text4 = findViewById(R.id.editT4);
        Text5 = findViewById(R.id.editT5);
       Text6 = findViewById(R.id.editT6);

    }
    //method for adding a new movie
    public void save(View view){
        try{
        if(!(Text1.getText().toString().isEmpty()||Text2.getText().toString().isEmpty()||Text3.getText().toString().isEmpty()||Text4.getText().toString().isEmpty()||Text5.getText().toString().isEmpty()||Text6.getText().toString().isEmpty())){
            int rates=Integer.parseInt(String.valueOf(Text5.getText()));
            int yr=Integer.parseInt(String.valueOf(Text2.getText()));
       if(rates<=10 && yr>1895) {
           boolean insert = db.insert_movies(Text1.getText().toString(), Text2.getText().toString(), Text3.getText().toString(), Text4.getText().toString(), Integer.parseInt(String.valueOf(Text5.getText())), Text6.getText().toString());

           Cursor movies = db.get_movies();
           if (insert) {
               Log.d("xyz", String.valueOf(movies.getCount()));
               Log.d("xyz", "Added a new Movie");
               Toast.makeText(RegisterMovie.this, "A movie is added to watched movies list", Toast.LENGTH_SHORT).show();
               Text1.setText("");
               Text2.setText("");
              Text3.setText("");
              Text4.setText("");
               Text5.setText("");
              Text6.setText("");
           }
       }else{
           Toast.makeText(this,"Give Valid year and rate",Toast.LENGTH_SHORT).show();
       }
        }else{
            Toast.makeText(this, "Fill all the textFields", Toast.LENGTH_SHORT).show();
        }
}catch (Exception ex){
            Toast.makeText(this,"Give valid Values",Toast.LENGTH_SHORT).show();
        }
    }
}