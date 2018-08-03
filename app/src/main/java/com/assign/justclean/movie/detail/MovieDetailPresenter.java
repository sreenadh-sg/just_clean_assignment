package com.assign.justclean.movie.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;
import com.assign.justclean.movie.MovieDisplayContract;
import com.assign.justclean.movie.MovieFetchingHandler;
import com.assign.justclean.mvp.BasePresenter;
import com.assign.justclean.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class MovieDetailPresenter extends BasePresenter<MovieDetailContract.MovieDetailView> implements
        MovieDetailContract.MovieDetailPresenter {
    private static final String TAG = "MoviePresenter";

    MovieFetchingHandler movieFetchingHandler;

    private final SchedulersFacade schedulersFacade;

    public MovieDetailPresenter(MovieDetailContract.MovieDetailView view, MovieFetchingHandler
            movieFetchingHandler, SchedulersFacade schedulersFacade){
        super(view);
        this.movieFetchingHandler=movieFetchingHandler;
        this.schedulersFacade=schedulersFacade;
    }


    @Override
    public void fetchMovie(int movieID) {

        view.startProgressBar();


        addDisposible(movieFetchingHandler.getMovieObservable(String.valueOf(movieID)).subscribeOn
                (schedulersFacade
                .io())
                .observeOn(schedulersFacade.ui()).subscribeWith(getMovieObserver()));



    }

    @Override
    public void stop() {
        super.stop();
    }

    public DisposableObserver<Movie> getMovieObserver(){
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(Movie value) {
                view.displayMovie(value);
            }

            @Override
            public void onError(Throwable e) {
                view.displayMovieError(e);
            }

            @Override
            public void onComplete() {
                view.stopProgressBar();
            }
        };
    }





}
