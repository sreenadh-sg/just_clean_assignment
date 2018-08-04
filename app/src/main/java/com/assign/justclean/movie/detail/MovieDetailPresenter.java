/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;
import com.assign.justclean.movie.MovieDisplayContract;
import com.assign.justclean.movie.MovieFetchingHandler;
import com.assign.justclean.mvp.BasePresenter;
import com.assign.justclean.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class MovieDetailPresenter extends BasePresenter<MovieDetailContract.MovieDetailView> implements
        MovieDetailContract.MovieDetailPresenter {
    private static final String TAG = "MoviePresenter";

    MovieFetchingHandler movieFetchingHandler;

    private final SchedulersFacade schedulersFacade;

    /**
     *
     * @param view MovieDetailContract.MovieDetailView for handling view operation
     * @param movieFetchingHandler MovieFetchingHandler for api handling
     * @param schedulersFacade Scheduler for Rx event
     */
    public MovieDetailPresenter(MovieDetailContract.MovieDetailView view, MovieFetchingHandler
            movieFetchingHandler, SchedulersFacade schedulersFacade){
        super(view);
        this.movieFetchingHandler=movieFetchingHandler;
        this.schedulersFacade=schedulersFacade;
    }

    /**
     *
     * @param movieID movieID obtained from server
     */
    @Override
    public void fetchMovie(int movieID) {

        view.startProgressBar();


        //add disposable to CompositeDisposable
        addDisposible(movieFetchingHandler.getMovieObservable(String.valueOf(movieID)).subscribeOn
                (schedulersFacade
                .io())
                .observeOn(schedulersFacade.ui()).subscribeWith(getMovieObserver()));



    }

    @Override
    public void stop() {
        super.stop();
    }

    /**
     *
     * @return returning Observer of type MovieResponse
     */
    public DisposableObserver<Movie> getMovieObserver(){
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(Movie value) {
                view.displayMovie(value);
            }

            @Override
            public void onError(Throwable e) {
                view.displayMovieError(e);
            }

            @Override
            public void onComplete() {
                view.stopProgressBar();
            }
        };
    }





}
