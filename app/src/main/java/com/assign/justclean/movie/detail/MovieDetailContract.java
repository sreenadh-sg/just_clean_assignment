package com.assign.justclean.movie.detail;

import com.assign.justclean.model.Movie;

public interface MovieDetailContract {

    public interface  MovieDetailView {
        //event fired
        void fetchMovie(int movieID);
        void startProgressBar();
        void stopProgressBar();

        //handling movies
        void displayMovie(Movie movie);
        //handling error action
        void displayMovieError(Throwable throwable);

    }

    public interface MovieDetailPresenter{
        void fetchMovie(int movieID);
    }
}
