package com.assign.justclean.movie;

import android.support.annotation.NonNull;
import android.util.Log;

import com.assign.justclean.model.MovieResponse;
import com.assign.justclean.mvp.BasePresenter;
import com.assign.justclean.rx.SchedulersFacade;

import io.reactivex.observers.DisposableObserver;

public class MoviePresenter extends BasePresenter<MovieDisplayContract.MovieView> implements
        MovieDisplayContract.MoviePresenter {
    private static final String TAG = "MoviePresenter";

    MovieFetchingHandler movieFetchingHandler;

    private final SchedulersFacade schedulersFacade;

    public MoviePresenter(MovieDisplayContract.MovieView view,MovieFetchingHandler
            movieFetchingHandler,SchedulersFacade schedulersFacade){
        super(view);
        this.movieFetchingHandler=movieFetchingHandler;
        this.schedulersFacade=schedulersFacade;
    }


    @Override
    public void fetchMovie(int type) {

        view.startProgressBar();

        String movieType="";
        if(type==0)
            movieType="popular";
        else if (type==1)
            movieType="top_rated";
        else
            movieType="upcoming";

        addDisposible(movieFetchingHandler.getObservable(movieType).subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui()).subscribeWith(getObserver()));

    }

    @Override
    public void stop() {
        super.stop();
    }

    public DisposableObserver<MovieResponse> getObserver(){
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                view.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                e.printStackTrace();
                view.displayMovieError(e);
            }

            @Override
            public void onComplete() {
                view.stopProgressBar();
                Log.d(TAG,"Completed");
            }
        };
    }



}
