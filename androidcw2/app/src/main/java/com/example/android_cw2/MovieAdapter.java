package com.example.android_cw2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<String> movie_names;
    private List<String> movie_id;
    public int[] movie_fav;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;//https://www.youtube.com/watch?v=7nrLiFALU4s&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=21&ab_channel=CodeCampSriLanka



    // data is passed into the constructor
    public MovieAdapter(Context context, String[] movie_names,String[] movie_ids,int[] movie_fav) {

        this.context = context;
        this.movie_names = new ArrayList<>(Arrays.asList(movie_names));
        this.movie_id = new ArrayList<>(Arrays.asList(movie_ids));
        this.movie_fav = movie_fav;
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.row_movie, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.movie_name.setText(movie_names.get(position));

        if(movie_fav[position] == 1){
            holder.checkBox.setChecked(true);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    System.out.println("Checked");
                    movie_fav[position] = 1;
                } else {
                    movie_fav[position] = 0;
                    System.out.println("Un-Checked");
                }
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
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            movie_name =  itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
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
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
