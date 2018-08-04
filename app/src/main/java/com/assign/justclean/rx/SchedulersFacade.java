/*
 * Created by Sreenadh S Pillai on 04/08/18 11:55
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:43
 */

package com.assign.justclean.rx;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Custom class for remove android dependency in Presenter Layer
 */
public class SchedulersFacade {

    @Inject
    public SchedulersFacade() {
    }

    /**
     * IO thread pool scheduler
     */
    public Scheduler io() {
        return Schedulers.io();
    }

    /**
     * Computation thread pool scheduler
     */
    public Scheduler computation() {
        return Schedulers.computation();
    }

    /**
     * Main Thread scheduler
     */
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}