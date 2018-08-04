/*
 * Created by Sreenadh S Pillai on 04/08/18 11:55
 * Copyright (c) 2018 . All rights reserved
 * Last modified 04/08/18 11:43
 */

package com.assign.justclean.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V> {

    /**
     * presenter view
     */
    protected final V view;
    private CompositeDisposable disposables=new CompositeDisposable();

    /**
     *
     * @param view presenter view
     */
    public BasePresenter(V view) {
        this.view = view;
    }

    public void start(){

    }

    /**
     * clearing all CompositeDisposable if it's present
     */
    public void stop(){
        disposables.clear();
    }

    /**
     *
     * @param disposable  add disposable to observe the changes
     */
    public void addDisposible(Disposable disposable){
        disposables.add(disposable);
    }
}
