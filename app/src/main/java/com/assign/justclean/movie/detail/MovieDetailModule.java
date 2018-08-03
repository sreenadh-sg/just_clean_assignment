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

    @Provides
    Retrofit provideRetrofit() {
        return RetrofitClientInstance.getRetrofit();
    }

    @Provides
    MovieDetailPresenter provideMovieDetailPresenter(MovieDetailContract.MovieDetailView movieView,
                                                                  MovieFetchingHandler movieFetchingHandler,
                                                                  SchedulersFacade schedulersFacade) {
        return new MovieDetailPresenter(movieView, movieFetchingHandler, schedulersFacade);
    }



}
