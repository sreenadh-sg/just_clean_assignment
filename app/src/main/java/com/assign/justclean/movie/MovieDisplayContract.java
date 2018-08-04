/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie;

import com.assign.justclean.model.MovieResponse;

public class MovieDisplayContract {

    public interface  MovieView {
        /**
         * fetching popular movies
         */
        void fetchPopularMovies();

        /**
         * fetching top rated movies
         */
        void fetchTopRatedMovies();

        /**
         * fetching upcoming movie
         */
        void fetchUpcomingMovies();

        /**
         * start progressbar when Rx Event started
         */
        void startProgressBar();

        /**
         * stop progressbar when Rx Event stopped
         */
        void stopProgressBar();

        /**
         *
         * @param movieResponse  displaying requested type of movies returned from server ,this method execute for success
         */
        void displayMovies(MovieResponse movieResponse);

        /**
         *
         * @param throwable While api call if some error occurred this method get call
         */
        void displayMovieError(Throwable throwable);

    }

    /**
     * MoviePresenter provides communicate between MovieView  and Model
     */
    public interface MoviePresenter{
        void fetchMovie(int type);
    }
}
