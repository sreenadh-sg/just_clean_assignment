package com.assign.justclean.movie.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assign.justclean.R;
import com.assign.justclean.glide.GlideApp;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.model.Movie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context context;


    public MoviesAdapter(Context context,List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.context=context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView movieNameTV, releaseDateTV, ratingTV;
        public ImageView posterImage;

        public MyViewHolder(View view) {
            super(view);
            movieNameTV = (TextView) view.findViewById(R.id.movie_name);
            releaseDateTV = (TextView) view.findViewById(R.id.release_date);
            ratingTV = (TextView) view.findViewById(R.id.movie_rating);
            posterImage=view.findViewById(R.id.poster_image);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_holder_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.movieNameTV.setText(movie.getTitle());
        holder.releaseDateTV.setText(formatDate(movie.getReleaseDate()));
        String ratingText=String.valueOf(movie.getVoteAverage())+"/10";
        holder.ratingTV.setText(ratingText);
        String imageServerUrl= AppConstants.MOVIE_IMAGE_URL_PATH+movie.getPosterPath();
        GlideApp.with(context)
                .load(imageServerUrl).placeholder(R.drawable.placeholder)
                .override(holder.posterImage.getWidth(), holder.posterImage.getHeight())
                .into(holder.posterImage);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public String formatDate(String unFormattedDate){
        String formattedDate="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = new Date(sdf.parse(unFormattedDate).getTime());
            sdf.applyPattern("dd MMM yyyy");
            return sdf.format(date);

        }catch (Exception exp){

            return "";

        }
    }
}
