/*
 * Created by Sreenadh S Pillai on 04/08/18 11:55
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:43
 */

package com.assign.justclean.movie;

import com.assign.justclean.api.RetrofitClientInstance;
import com.assign.justclean.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieModule {

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
     * @param movieView MovieDisplayContract.MovieView
     * @param movieFetchingHandler MovieFetchingHandler for api call
     * @param schedulersFacade Scheduler for Rx event
     * @return
     */
    @Provides
    MoviePresenter provideMoviePresenter(MovieDisplayContract.MovieView movieView,
                                         MovieFetchingHandler movieFetchingHandler,
                                         SchedulersFacade schedulersFacade) {
        return new MoviePresenter(movieView, movieFetchingHandler, schedulersFacade);
    }



}
