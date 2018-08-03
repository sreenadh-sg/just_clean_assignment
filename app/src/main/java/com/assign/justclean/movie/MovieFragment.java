package com.assign.justclean.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assign.justclean.R;
import com.assign.justclean.glide.GlideApp;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.model.Movie;
import com.assign.justclean.movie.detail.MovieDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MovieFragment extends Fragment implements View.OnClickListener {

    //selected movie for display
    Movie movie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(this);
        Bundle receiveBundle=getArguments();
        TextView nameTV=view.findViewById(R.id.movie_name);
        TextView movieLengthTV=view.findViewById(R.id.movie_length);
        TextView movieReleaseDateTV=view.findViewById(R.id.release_date);

        ImageView movieImage=view.findViewById(R.id.movie_image);

        if(receiveBundle!=null&&!receiveBundle.isEmpty()){
            movie=receiveBundle.getParcelable(AppConstants.SELECTED_MOVIE);
            if(movie!=null) {
                nameTV.setText(movie.getTitle());
                String imageServerUrl=AppConstants.MOVIE_IMAGE_URL_PATH+movie.getPosterPath();
                GlideApp.with(this)
                        .load(imageServerUrl).placeholder(R.drawable.placeholder)
                        .override(movieImage.getWidth(), movieImage.getHeight())
                        .into(movieImage);

                String voteAvg=getString(R.string.vote_avg)+" "+movie.getVoteAverage();
                movieLengthTV.setText(voteAvg);
                String releaseDate=getString(R.string.relase_date)+" "+formatDate(movie
                        .getReleaseDate());
                movieReleaseDateTV.setText(releaseDate);
            }
        }



        //Glide.with(this).
    }

    @Override
    public void onClick(View view) {
        startMovieDetailScreen();


    }

    public void startMovieDetailScreen(){
        if(movie!=null) {
            Intent movieDetailsIntent = new Intent(getContext(), MovieDetailActivity.class);
            movieDetailsIntent.putExtra(AppConstants.MOVIE_ID,movie.getId());
            movieDetailsIntent.putExtra(AppConstants.SELECTED_MOVIE_NAME,movie.getTitle());
            startActivity(movieDetailsIntent);
        }
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
