package com.assign.justclean.api;


import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("movie/{type}")
    Observable<MovieResponse> getMovies(@Path("type") String typeName, @Query("api_key") String
            api_key);
    @GET("movie/{id}")
    Observable<Movie> getMovie(@Path("id") String movieID, @Query("api_key") String
            api_key);
}
