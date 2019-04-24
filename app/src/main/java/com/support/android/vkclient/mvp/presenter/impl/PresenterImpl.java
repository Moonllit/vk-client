package com.support.android.vkclient.mvp.presenter.impl;

import com.support.android.vkclient.mvp.presenter.Presenter;
import com.support.android.vkclient.mvp.view.MvpView;

import androidx.annotation.Nullable;
import io.reactivex.disposables.CompositeDisposable;

public abstract class PresenterImpl<T extends MvpView> implements Presenter<T> {

    @Nullable
    protected T view;
    protected CompositeDisposable disposables;

    @Override
    public void attachView(T view) {
        this.view = view;
        disposables = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onDestroy() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
