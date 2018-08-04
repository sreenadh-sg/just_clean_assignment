/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie;

import com.assign.justclean.api.NetworkInterface;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MovieFetchingHandler {

    public Retrofit retrofit;

    /**
     *
     * @param retrofit initiated by dagger
     */
    @Inject
    public MovieFetchingHandler(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    /**
     *
     * @param type movie type for fetching from sever (eg:popular)
     * @return
     */
    public Observable<MovieResponse> getObservable(String type){
        return retrofit.create(NetworkInterface.class)
                .getMovies(type, AppConstants.API_KEY);
    }

    /**
     *
     * @param movieID movie id for fetching more details about selected movie
     * @return Movie
     */
    public Observable<Movie> getMovieObservable(String movieID){
        return retrofit.create(NetworkInterface.class)
                .getMovie(movieID, AppConstants.API_KEY);
    }

    /**
     *
     * @param query movie name for searching
     * @return MovieResponse
     */
    public Observable<MovieResponse> searchMovieObservable(String query){
        return retrofit.create(NetworkInterface.class)
                .searchMovie(AppConstants.API_KEY,query);
    }


}
