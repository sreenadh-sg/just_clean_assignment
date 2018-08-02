package com.assign.justclean.movie;

import com.assign.justclean.api.RetrofitClientInstance;
import com.assign.justclean.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieModule {

    @Provides
    Retrofit provideRetrofit() {
        return RetrofitClientInstance.getRetrofit();
    }

    @Provides
    MoviePresenter provideMoviePresenter(MovieDisplayContract.MovieView movieView,
                                         MovieFetchingHandler movieFetchingHandler,
                                         SchedulersFacade schedulersFacade) {
        return new MoviePresenter(movieView, movieFetchingHandler, schedulersFacade);
    }



}
