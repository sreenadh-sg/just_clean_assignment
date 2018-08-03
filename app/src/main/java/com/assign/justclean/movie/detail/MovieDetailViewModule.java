package com.assign.justclean.movie.detail;

import com.assign.justclean.movie.MovieDisplayContract;
import com.assign.justclean.movie.MoviesFragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieDetailViewModule {

  @Binds
  abstract MovieDetailContract.MovieDetailView provideMovieDetailView(MovieDetailActivity movieDetailActivity);
}