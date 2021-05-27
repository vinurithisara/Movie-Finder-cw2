package com.example.android_cw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rating extends AppCompatActivity {

    DBHandler db;
    ArrayList<String> movie_names, movie_ids;

    IMDBResultAdapter adapter;
    String id;
    String image_url;
    String title;
    TextView rating, title_txt;
    static ImageView image;
    DownloadImage downloadImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        title = getIntent().getStringExtra("title");
        image_url = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");

        title_txt = findViewById(R.id.title);
        rating = findViewById(R.id.rating);
        image = findViewById(R.id.imageView);
        title_txt.setText(title);

        subjectload();

        imageLoad();
    }

    private void imageLoad() {

            downloadImage=new DownloadImage();
            downloadImage.execute(image_url);

    }

    public void subjectload() {

        String movie_url = "https://imdb-api.com/en/API/UserRatings/k_543b53m7/" + id;

        final StringRequest jar = new StringRequest(Request.Method.GET, movie_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myz", "Response : " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String total_rating = jsonObject.getString("totalRating");
                            rating.setText("Total Rating : "+total_rating);
                            Log.d("xyz" , "no error :");
                        } catch (JSONException e) {
                            Toast.makeText(Rating.this, "Something Wrong, Go Back and try again", Toast.LENGTH_LONG).show();
                            Log.d("xyz" , "json error :" + e.getMessage() );
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("myz", "Error: " + error);

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MySingleton.getInstance().addRequest(jar);

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;

        }
    }
}