/*
 * Created by Sreenadh S Pillai on 04/08/18 11:58
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:43
 */

package com.assign.justclean.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    //retrofit instance
    private static Retrofit retrofit;

    //Get Retrofit  instance for api call

    /**
     *
     * @return  Get Retrofit  instance for api call
     */
    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

        }

        return retrofit;
    }
}
