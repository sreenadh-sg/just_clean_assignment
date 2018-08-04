/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.detail;

import com.assign.justclean.model.Movie;

public interface MovieDetailContract {

    public interface  MovieDetailView {
        /**
         *
         * @param movieID user selected movie id
         */
        void fetchMovie(int movieID);

        /**
         * start progress for while movie fetching
         */
        void startProgressBar();

        /**
         * stop progress bar when api call complete.
         */
        void stopProgressBar();

        /**
         *
         * @param movie movie instance returned from the server
         */
        void displayMovie(Movie movie);

        /**
         *
         * @param throwable error information ,while perform api call if error is occurred this method get executed
         */
        void displayMovieError(Throwable throwable);

    }

    public interface MovieDetailPresenter{
        /**
         *
         * @param movieID movieID obtained from server
         */
        void fetchMovie(int movieID);
    }
}
