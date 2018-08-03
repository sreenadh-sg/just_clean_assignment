package com.assign.justclean.movie.search;

import com.assign.justclean.model.Movie;

import java.util.List;

public interface MovieSearchContract {

    public interface  MovieSearchView {
        //event fired
        void startProgressBar();
        void stopProgressBar();

        //handling movies
        void displayMovies(List<Movie> movies);
        //handling error action
        void displayMovieError(Throwable throwable);

    }

    public interface MovieSearchPresenter{
        void searchMovie(String query);
    }
}
