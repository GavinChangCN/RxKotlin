package com.gavin.rxbus;

import rx.Observable;

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-06-14
 * Time: 11:17
 */
public class ObservableWrapper<T> {

    protected static final String TAG = "ObservableWrapper";

    private Observable<T> observable;
    private Object tag;

    public ObservableWrapper(Object tag, Observable<T> observable) {
        this.tag = tag;
        this.observable = observable;
    }

    public Observable<T> getObservable() {
        return observable;
    }

    public void setObservable(Observable<T> observable) {
        this.observable = observable;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

}
