/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.detail;

import com.assign.justclean.api.RetrofitClientInstance;
import com.assign.justclean.movie.MovieFetchingHandler;
import com.assign.justclean.movie.MoviePresenter;
import com.assign.justclean.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieDetailModule {

    /**
     *
     * @return provide RetrofitClientInstance when dependency injection happened
     */
    @Provides
    Retrofit provideRetrofit() {
        return RetrofitClientInstance.getRetrofit();
    }

    /**
     *
     * @param movieView MovieDetailContract.MovieDetailView
     * @param movieFetchingHandler MovieFetchingHandler for api call
     * @param schedulersFacade Scheduler for Rx event
     * @return
     */
    @Provides
    MovieDetailPresenter provideMovieDetailPresenter(MovieDetailContract.MovieDetailView movieView,
                                                                  MovieFetchingHandler movieFetchingHandler,
                                                                  SchedulersFacade schedulersFacade) {
        return new MovieDetailPresenter(movieView, movieFetchingHandler, schedulersFacade);
    }



}
