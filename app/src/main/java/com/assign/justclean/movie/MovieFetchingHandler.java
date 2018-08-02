package com.assign.justclean.movie;

import com.assign.justclean.api.NetworkInterface;
import com.assign.justclean.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .getMovies(type,"cbc7db9ff286a6bd1f954e817cc828d1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
