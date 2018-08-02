package com.assign.justclean.movie;

import com.assign.justclean.model.MovieResponse;

public class MovieDisplayContract {

    public interface  MovieView {
        //event fired
        void fetchPopularMovies();
        void fetchTopRatedMovies();
        void fetchUpcomingMovies();
        void startProgressBar();
        void stopProgressBar();

        //handling movies
        void displayMovies(MovieResponse movieResponse);
        //handling error action
        void displayMovieError(Throwable throwable);

    }

    public interface MoviePresenter{
        void fetchMovie(int type);
    }
}
