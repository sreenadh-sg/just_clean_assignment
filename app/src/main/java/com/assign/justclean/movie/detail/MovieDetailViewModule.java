/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.detail;

import com.assign.justclean.movie.MovieDisplayContract;
import com.assign.justclean.movie.MoviesFragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieDetailViewModule {

  /**
   *
   * @param movieDetailActivity MovieDetailActivity instance
   * @return MovieDisplayContract.MovieView
   */
  @Binds
  abstract MovieDetailContract.MovieDetailView provideMovieDetailView(MovieDetailActivity movieDetailActivity);
}