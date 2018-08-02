package com.assign.justclean.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V> {

    protected final V view;
    private CompositeDisposable disposables=new CompositeDisposable();

    public BasePresenter(V view) {
        this.view = view;
    }

    public void start(){

    }
    public void stop(){
        disposables.clear();
    }

    public void addDisposible(Disposable disposable){
        disposables.add(disposable);
    }
}
