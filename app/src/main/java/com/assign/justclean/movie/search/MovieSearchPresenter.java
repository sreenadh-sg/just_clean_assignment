/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.search;

import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;
import com.assign.justclean.movie.MovieFetchingHandler;
import com.assign.justclean.movie.detail.MovieDetailContract;
import com.assign.justclean.mvp.BasePresenter;
import com.assign.justclean.rx.SchedulersFacade;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MovieSearchPresenter extends BasePresenter<MovieSearchContract.MovieSearchView> implements
        MovieSearchContract.MovieSearchPresenter {
    private static final String TAG = "MoviePresenter";

    MovieFetchingHandler movieFetchingHandler;

    private final SchedulersFacade schedulersFacade;

    public MovieSearchPresenter(MovieSearchContract.MovieSearchView view, MovieFetchingHandler
            movieFetchingHandler, SchedulersFacade schedulersFacade){
        super(view);
        this.movieFetchingHandler=movieFetchingHandler;
        this.schedulersFacade=schedulersFacade;
    }




    @Override
    public void searchMovie(String query) {

        view.startProgressBar();


        addDisposible(movieFetchingHandler.searchMovieObservable(query).subscribeOn
                (schedulersFacade
                        .io())
                .observeOn(schedulersFacade.ui()).subscribeWith(getMoviesListObserver()));
    }

    @Override
    public void stop() {
        super.stop();
    }

    public DisposableObserver<MovieResponse> getMoviesListObserver(){
        return new DisposableObserver<MovieResponse>() {
            @Override
            public void onNext(MovieResponse value) {
                if(value!=null)
                    view.displayMovies(value.getResults());
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
