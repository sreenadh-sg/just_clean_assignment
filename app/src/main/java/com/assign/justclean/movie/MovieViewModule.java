/*
 * Created by Sreenadh S Pillai on 04/08/18 11:55
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:43
 */

package com.assign.justclean.movie;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieViewModule {

  /**
   *
   * @param movieFragment MovieFragment instance
   * @return MovieDisplayContract.MovieView
   */
  @Binds
  abstract MovieDisplayContract.MovieView provideMovieView(MoviesFragment movieFragment);
}