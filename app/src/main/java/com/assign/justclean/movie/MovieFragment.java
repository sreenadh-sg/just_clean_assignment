package com.assign.justclean.movie;

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
import com.assign.justclean.model.Movie;


public class MovieFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle receiveBundle=getArguments();
        TextView nameTV=view.findViewById(R.id.movie_name);
        TextView movieLength=view.findViewById(R.id.movie_length);
        TextView movieReleaseDate=view.findViewById(R.id.release_date);

        ImageView movieImage=view.findViewById(R.id.movie_image);

        if(receiveBundle!=null&&!receiveBundle.isEmpty()){
            Movie movie=receiveBundle.getParcelable("MOVIE");
            if(movie!=null) {
                nameTV.setText(movie.getTitle());
                String imageServerUrl="https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
                GlideApp.with(this)
                        .load(imageServerUrl)
                        .override(movieImage.getWidth(), movieImage.getHeight())
                        .into(movieImage);
                movieLength.setText("2Hrs");
                movieReleaseDate.setText(movie.getReleaseDate());
            }
        }



        //Glide.with(this).
    }
}
