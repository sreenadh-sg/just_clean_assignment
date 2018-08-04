/*
 * Created by Sreenadh S Pillai on 04/08/18 11:51
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:43
 */

package com.assign.justclean;

import android.app.Activity;
import android.app.Application;


import com.assign.justclean.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * initialize DaggerAppComponent
         */
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
