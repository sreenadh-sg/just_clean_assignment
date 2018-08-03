package com.assign.justclean.movie;

import com.assign.justclean.api.NetworkInterface;
import com.assign.justclean.misc.AppConstants;
import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MovieFetchingHandler {

    public Retrofit retrofit;

    @Inject
    public MovieFetchingHandler(Retrofit retrofit) {
        this.retrofit = retrofit;
    }



    public Observable<MovieResponse> getObservable(String type){
        return retrofit.create(NetworkInterface.class)
                .getMovies(type, AppConstants.API_KEY);
    }
    public Observable<Movie> getMovieObservable(String movieID){
        return retrofit.create(NetworkInterface.class)
                .getMovie(movieID, AppConstants.API_KEY);
    }
    public Observable<MovieResponse> searchMovieObservable(String query){
        return retrofit.create(NetworkInterface.class)
                .searchMovie(AppConstants.API_KEY,query);
    }


}
