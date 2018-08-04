/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.search;

import com.assign.justclean.model.Movie;

import java.util.List;

public interface MovieSearchContract {

    public interface  MovieSearchView {
        /**
         * start progressbar when api call performs
         */
        void startProgressBar();

        /**
         * stop progressbar when api call completes
         */
        void stopProgressBar();

        /**
         *
         * @param movies displaying list of movies according to user query
         */
        void displayMovies(List<Movie> movies);
        /**
         *
         * @param throwable error information ,while perform api call if error is occurred this method get executed
         */
        void displayMovieError(Throwable throwable);

    }

    public interface MovieSearchPresenter{
        void searchMovie(String query);
    }
}
