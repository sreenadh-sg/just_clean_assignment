/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.search;

import com.assign.justclean.api.RetrofitClientInstance;
import com.assign.justclean.movie.MovieFetchingHandler;
import com.assign.justclean.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieSearchModule {

    @Provides
    Retrofit provideRetrofit() {
        return RetrofitClientInstance.getRetrofit();
    }

    @Provides
    MovieSearchPresenter provideMovieDetailPresenter(MovieSearchContract.MovieSearchView movieView,
                                                                         MovieFetchingHandler movieFetchingHandler,
                                                                         SchedulersFacade schedulersFacade) {
        return new MovieSearchPresenter(movieView, movieFetchingHandler, schedulersFacade);
    }



}
