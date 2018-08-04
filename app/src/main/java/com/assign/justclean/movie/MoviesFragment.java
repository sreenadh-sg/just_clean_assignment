/*
 * Created by Sreenadh S Pillai on 04/08/18 11:55
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assign.justclean.R;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MoviesFragment extends Fragment implements MovieDisplayContract.MovieView {


    @Inject
    MoviePresenter presenter;

    ProgressBar loadMoviesPB;
    TextView resultStatusTV;
    ViewPager moviesViewPager;
    MovieSlidePagerAdapter movieSlidePagerAdapter;
    int movieType=-1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle receiveBundle=getArguments();

        initView(view);
        if(receiveBundle!=null&&!receiveBundle.isEmpty()){
            movieType=receiveBundle.getInt(AppConstants.SELECTED_MOVIE_LIST_TYPE,-1);
           if (movieType ==0)
              fetchPopularMovies();
           else if(movieType==1)
                fetchTopRatedMovies();
           else
               fetchUpcomingMovies();

        }



        movieSlidePagerAdapter = new MovieSlidePagerAdapter(getChildFragmentManager());
        moviesViewPager.setAdapter(movieSlidePagerAdapter);
    }

    public void initView(View view){
        moviesViewPager =  view.findViewById(R.id.pager);
        loadMoviesPB=view.findViewById(R.id.load_movies);
        resultStatusTV=view.findViewById(R.id.result_status);
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void fetchPopularMovies() {
        presenter.fetchMovie(movieType);

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void fetchTopRatedMovies() {

        presenter.fetchMovie(movieType);

    }

    @Override
    public void fetchUpcomingMovies() {
        presenter.fetchMovie(movieType);
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if(movieResponse!=null){
            List<Movie> result=movieResponse.getResults();
            if(result.isEmpty()){
                moviesViewPager.setVisibility(View.GONE);
                resultStatusTV.setVisibility(View.VISIBLE);
                resultStatusTV.setText(getString(R.string.no_data_found));
            } else {
                movieSlidePagerAdapter.setData(result);
                movieSlidePagerAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void startProgressBar() {
        moviesViewPager.setVisibility(View.INVISIBLE);
        loadMoviesPB.setVisibility(View.VISIBLE);
        resultStatusTV.setVisibility(View.GONE);

    }

    @Override
    public void stopProgressBar() {
        moviesViewPager.setVisibility(View.VISIBLE);
        loadMoviesPB.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayMovieError(Throwable throwable) {

        moviesViewPager.setVisibility(View.INVISIBLE);
        loadMoviesPB.setVisibility(View.INVISIBLE);
        resultStatusTV.setVisibility(View.VISIBLE);
        resultStatusTV.setText(getString(R.string.something_went_wrong));

    }


    private class MovieSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<Movie> movieList = new ArrayList<>();
        public MovieSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        public void setData(List<Movie> posters) {
            movieList.addAll(posters);
        }

        @Override
        public Fragment getItem(int position){
            Movie movie = movieList.get(position);
            Fragment fragment=new MovieFragment();
            Bundle arguments=new Bundle();
            arguments.putParcelable(AppConstants.SELECTED_MOVIE,movie);
            fragment.setArguments(arguments);
            return fragment;
        }

        @Override
        public int getCount(){
            return movieList.size();
        }

    }
}
