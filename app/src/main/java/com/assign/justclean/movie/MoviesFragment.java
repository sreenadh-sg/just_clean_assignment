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

import com.assign.justclean.R;
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
        moviesViewPager =  view.findViewById(R.id.pager);
        loadMoviesPB=view.findViewById(R.id.load_movies);
        if(receiveBundle!=null&&!receiveBundle.isEmpty()){
            movieType=receiveBundle.getInt("SELECTED_TYPE",-1);
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
            Log.v("Test","Size - "+result.size());
            movieSlidePagerAdapter.setData(result);
            movieSlidePagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void startProgressBar() {
        moviesViewPager.setVisibility(View.INVISIBLE);
        loadMoviesPB.setVisibility(View.VISIBLE);

    }

    @Override
    public void stopProgressBar() {
        moviesViewPager.setVisibility(View.VISIBLE);
        loadMoviesPB.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayMovieError(Throwable throwable) {

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
            arguments.putParcelable("MOVIE",movie);
            fragment.setArguments(arguments);
            return fragment;
        }

        @Override
        public int getCount(){
            return movieList.size();
        }

    }
}
