package com.assign.justclean.di;

import com.assign.justclean.MainActivity;
import com.assign.justclean.movie.MovieActivity;
import com.assign.justclean.movie.MovieModule;
import com.assign.justclean.movie.MovieViewModule;
import com.assign.justclean.movie.MoviesFragment;
import com.assign.justclean.movie.detail.MovieDetailActivity;
import com.assign.justclean.movie.detail.MovieDetailModule;
import com.assign.justclean.movie.detail.MovieDetailViewModule;
import com.assign.justclean.movie.search.MovieSearchActivity;
import com.assign.justclean.movie.search.MovieSearchModule;
import com.assign.justclean.movie.search.MovieSearchViewModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector()
    abstract MovieActivity bindMovieActivity();

    @ContributesAndroidInjector(modules = {MovieViewModule.class, MovieModule.class})
    abstract MoviesFragment bindMovieFragment();
    @ContributesAndroidInjector(modules = {MovieDetailViewModule.class, MovieDetailModule.class})
    abstract MovieDetailActivity bindMovieDetailActivity();
    @ContributesAndroidInjector(modules = {MovieSearchViewModule.class, MovieSearchModule.class})
    abstract MovieSearchActivity bindMovieSearchActivity();
}
