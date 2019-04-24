package com.support.android.vkclient.mvp;

import io.reactivex.observers.DisposableObserver;

public class AppDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
