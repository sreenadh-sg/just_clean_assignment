/*
 * Created by Sreenadh S Pillai on 04/08/18 11:56
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.movie.search;

import com.assign.justclean.movie.detail.MovieDetailActivity;
import com.assign.justclean.movie.detail.MovieDetailContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieSearchViewModule {

  /**
   *
   * @param movieSearchActivity MovieSearchActivity instance
   * @return MovieDisplayContract.MovieView
   */
  @Binds
  abstract MovieSearchContract.MovieSearchView provideMovieSearchView(MovieSearchActivity movieSearchActivity);
}