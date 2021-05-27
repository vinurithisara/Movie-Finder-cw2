package com.example.android_cw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.ViewHolder> {
    private List<String> movie_names;
    private List<String> movie_id;
    public int[] movie_fav;
    private LayoutInflater mInflater;
    private Activity6 context;
    private MovieAdapter.ItemClickListener mClickListener;
//https://www.youtube.com/watch?v=y2xtLqP8dSQ&ab_channel=CodinginFlow
    //https://www.youtube.com/watch?v=e3MDW87mbR8
    // data is passed into the constructor
    public RatingsAdapter(Context context, String[] movie_names, String[] movie_ids) {

        this.context = (Activity6)context;
        this.movie_names = new ArrayList<>(Arrays.asList(movie_names));
        this.movie_id = new ArrayList<>(Arrays.asList(movie_ids));
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the cell layout
    @Override
    public RatingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.raw_edit_movie, parent, false);
        return new RatingsAdapter.ViewHolder(view);
    }

    // binds the data to the textview
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final RatingsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.movie_name.setText(movie_names.get(position));
        holder.movie_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, imdb.class);

                intent2.putExtra("title", movie_names.get(position));

                context.startActivity(intent2);
            }
        });

    }

    public  void onActivityResult(int requestCode, int resultCode) {
        this.notifyDataSetChanged();
        Log.d("myz" , "Changes happen on complete adapter");
    }


    // total number of cells
    @Override
    public int getItemCount() {
        return movie_names.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movie_name;

        ViewHolder(View itemView) {
            super(itemView);
            movie_name =  itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {


            if (mClickListener != null) {

                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    // convenience method for getting data at click position
    public String getItem(int id) {
        return movie_id.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(MovieAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
