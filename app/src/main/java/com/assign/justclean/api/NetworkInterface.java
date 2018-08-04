/*
 * Created by Sreenadh S Pillai on 04/08/18 11:58
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:47
 */

package com.assign.justclean.api;


import com.assign.justclean.model.Movie;
import com.assign.justclean.model.MovieResponse;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {
    /**
     *
     * @param typeName type of movies required from server
     * @param api_key  api key for fetching data from server
     * @return MovieResponse
     */
    @GET("movie/{type}")
    Observable<MovieResponse> getMovies(@Path("type") String typeName, @Query("api_key") String
            api_key);

    /**
     *
     * @param movieID movie id for fetching
     * @param api_key api key for fetching data from server
     * @return Movie
     */
    @GET("movie/{id}")
    Observable<Movie> getMovie(@Path("id") String movieID, @Query("api_key") String
            api_key);

    /**
     *
     * @param api_key api key for fetching data from server
     * @param query movie name for search
     * @return MovieResponse
     */
    @GET("search/movie")
    Observable<MovieResponse> searchMovie(@Query("api_key") String api_key,@Query("query") String query);
}
