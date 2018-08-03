package com.assign.justclean.movie.search;

import com.assign.justclean.movie.detail.MovieDetailActivity;
import com.assign.justclean.movie.detail.MovieDetailContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieSearchViewModule {

  @Binds
  abstract MovieSearchContract.MovieSearchView provideMovieSearchView(MovieSearchActivity movieSearchActivity);
}