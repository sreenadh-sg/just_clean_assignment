package com.assign.justclean.movie;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieViewModule {

  @Binds
  abstract MovieDisplayContract.MovieView provideMovieView(MoviesFragment movieFragment);
}