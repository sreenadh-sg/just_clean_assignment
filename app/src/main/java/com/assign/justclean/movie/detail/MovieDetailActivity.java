/*
 * Created by Sreenadh S Pillai on 04/08/18 11:57
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.detail;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.assign.justclean.R;
import com.assign.justclean.glide.GlideApp;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.model.Genre;
import com.assign.justclean.model.Movie;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class MovieDetailActivity extends AppCompatActivity  implements MovieDetailContract.MovieDetailView{

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ImageView posterIMV,coverIMV;
    TextView titleTV,descriptionTV,genreTV,popularityTV,ratingTV,revenueTV,adultTV,voteCountTV,
            voteAverageTV;
    ConstraintLayout movieDetailLayout;
    ProgressBar loadingProgressBar;

    TextView resultStatusTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //perform android injection
        AndroidInjection.inject(this);


        setContentView(R.layout.activity_movie_detail);



        //initializing all the views
        initView();
        //setting toolbar as action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        // movieID received from user selection
        int movieID=getIntent().getIntExtra(AppConstants.MOVIE_ID,-1);
        // movieName received from user selection
        String movieName = getIntent().getStringExtra(AppConstants.SELECTED_MOVIE_NAME);
        if(movieID>0) {
            //fetching more details
            fetchMovie(movieID);
        }

        collapsingToolbarLayout.setTitle(movieName);

        //setting cover image for selected movie
        GlideApp.with(this)
                .load(R.drawable.placeholder)
                .override(coverIMV.getWidth(), coverIMV.getHeight()).listener(new
                    RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        coverIMV.setImageDrawable(resource);
                        if(resource instanceof BitmapDrawable){

                            Palette.from(((BitmapDrawable) resource).getBitmap()).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(@NonNull Palette palette) {
                                    Palette.Swatch vibrantSwatch=palette.getDarkMutedSwatch();
                                    if(vibrantSwatch!=null){
                                        collapsingToolbarLayout.setContentScrimColor(vibrantSwatch.getRgb());
                                        toolbar.setTitleTextColor(vibrantSwatch.getTitleTextColor());
                                    }
                                }
                            });

                        }
                        return true;
                    }
                })
                .into(coverIMV);




    }
    public void initView(){
        posterIMV=findViewById(R.id.poster_image);
        coverIMV=findViewById(R.id.cover_image);
        titleTV=findViewById(R.id.title_text);
        genreTV=findViewById(R.id.genres);
        descriptionTV=findViewById(R.id.description);
        popularityTV=findViewById(R.id.popularity);
        ratingTV=findViewById(R.id.rating);
        revenueTV=findViewById(R.id.revenue);
        adultTV=findViewById(R.id.adult);
        voteCountTV=findViewById(R.id.vote_count);
        voteAverageTV=findViewById(R.id.vote_avg);
        movieDetailLayout=findViewById(R.id.movie_detail_view);
        toolbar =  findViewById(R.id.toolbar);
        collapsingToolbarLayout =  findViewById(R.id.collapsing_toolbar_layout);
        loadingProgressBar=findViewById(R.id.loading_progress_bar);
        resultStatusTV=findViewById(R.id.result_status);
    }

    @Override
    public void fetchMovie(int movieID) {
        movieDetailPresenter.fetchMovie(movieID);

    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        //handling action bar back button
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startProgressBar() {

        loadingProgressBar.setVisibility(View.VISIBLE);
        movieDetailLayout.setVisibility(View.GONE);
        resultStatusTV.setVisibility(View.GONE);

    }

    @Override
    public void stopProgressBar() {
        loadingProgressBar.setVisibility(View.GONE);
        movieDetailLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void displayMovie(Movie movie) {

        //setting server image url
        String coverPageUrl=AppConstants.MOVIE_IMAGE_URL_PATH+movie.getBackdropPath();

        //setting cover image for selected movie
        GlideApp.with(this)
                .load(coverPageUrl)
                .override(coverIMV.getWidth(), coverIMV.getHeight())
                .into(coverIMV);
        String posterImage=AppConstants.MOVIE_IMAGE_URL_PATH+movie.getPosterPath();
        //setting cover image for selected movie
        GlideApp.with(this)
                .load(posterImage)
                .override(posterIMV.getWidth(), posterIMV.getHeight()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                posterIMV.setImageDrawable(resource);

                if(resource instanceof BitmapDrawable){

                    Palette.from(((BitmapDrawable) resource).getBitmap()).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(@NonNull Palette palette) {
                            Palette.Swatch vibrantSwatch=palette.getDarkVibrantSwatch();
                            if(vibrantSwatch!=null){
                                //setting color from Palette
                                collapsingToolbarLayout.setContentScrimColor(vibrantSwatch.getRgb());
                                toolbar.setTitleTextColor(vibrantSwatch.getTitleTextColor());

                            }
                        }
                    });

                }
                return true;
            }
        })
                .into(posterIMV);
        titleTV.setText(movie.getOriginalTitle());
        String genresTxtForDisplay="";
        //setting genre name  from Genre list
        for (Genre genre:movie.getGenres()){
            genresTxtForDisplay=genresTxtForDisplay.isEmpty()?"":(genresTxtForDisplay+",");
            genresTxtForDisplay=genresTxtForDisplay+genre.name;
        }

        //displaying fetched movie details
        genreTV.setText(genresTxtForDisplay);
        descriptionTV.setText(movie.getOverview());
        popularityTV.setText(String.valueOf(movie.getPopularity()));
        ratingTV.setText(String.valueOf(movie.getVoteAverage()));
        revenueTV.setText(String.valueOf(movie.getRevenue()));
        adultTV.setText(movie.getAdult()?getString(R.string.yes_text):getString(R.string.no_text));
        voteCountTV.setText(String.valueOf(movie.getVoteCount()));
        voteAverageTV.setText(String.valueOf(movie.getVoteAverage()));







    }


    /**
     *
     * @param throwable error information ,while perform api call if error is occurred this method get executed
     */
    @Override
    public void displayMovieError(Throwable throwable) {
        movieDetailLayout.setVisibility(View.INVISIBLE);
        loadingProgressBar.setVisibility(View.INVISIBLE);
        resultStatusTV.setVisibility(View.VISIBLE);
        resultStatusTV.setText(getString(R.string.something_went_wrong));

    }


}
