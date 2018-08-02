package com.assign.justclean.di;

import com.assign.justclean.MainActivity;
import com.assign.justclean.movie.MovieActivity;
import com.assign.justclean.movie.MovieModule;
import com.assign.justclean.movie.MovieViewModule;
import com.assign.justclean.movie.MoviesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector()
    abstract MovieActivity bindMovieActivity();

    @ContributesAndroidInjector(modules = {MovieViewModule.class, MovieModule.class})
    abstract MoviesFragment bindMovieFragment();
}
