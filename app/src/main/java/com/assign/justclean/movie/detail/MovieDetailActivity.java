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
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);


        setContentView(R.layout.activity_movie_detail);


        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        int movieID=getIntent().getIntExtra(AppConstants.MOVIE_ID,-1);
        String movieName = getIntent().getStringExtra(AppConstants.SELECTED_MOVIE_NAME);
        if(movieID>0)
            fetchMovie(movieID);

        collapsingToolbarLayout.setTitle(movieName);

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
        progressBar=findViewById(R.id.loading_progress_bar);
    }

    @Override
    public void fetchMovie(int movieID) {
        movieDetailPresenter.fetchMovie(movieID);

    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
        movieDetailLayout.setVisibility(View.GONE);

    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.GONE);
        movieDetailLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void displayMovie(Movie movie) {

        Log.v("Restult","Result -"+movie.getTitle());

        String coverPageUrl=AppConstants.MOVIE_IMAGE_URL_PATH+movie.getBackdropPath();
        GlideApp.with(this)
                .load(coverPageUrl)
                .override(coverIMV.getWidth(), coverIMV.getHeight())
                .into(coverIMV);
        String posterImage=AppConstants.MOVIE_IMAGE_URL_PATH+movie.getPosterPath();
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
        for (Genre genre:movie.getGenres()){
            genresTxtForDisplay=genresTxtForDisplay.isEmpty()?"":(genresTxtForDisplay+",");
            genresTxtForDisplay=genresTxtForDisplay+genre.name;
        }
        genreTV.setText(genresTxtForDisplay);
        descriptionTV.setText(movie.getOverview());
        popularityTV.setText(String.valueOf(movie.getPopularity()));
        ratingTV.setText(String.valueOf(movie.getVoteAverage()));
        revenueTV.setText(String.valueOf(movie.getRevenue()));
        adultTV.setText(movie.getAdult()?getString(R.string.yes_text):getString(R.string.no_text));
        voteCountTV.setText(String.valueOf(movie.getVoteCount()));
        voteAverageTV.setText(String.valueOf(movie.getVoteAverage()));







    }



    @Override
    public void displayMovieError(Throwable throwable) {

    }


}
