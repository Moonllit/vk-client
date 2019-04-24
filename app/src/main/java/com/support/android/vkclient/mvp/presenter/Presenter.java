package com.support.android.vkclient.mvp.presenter;

import com.support.android.vkclient.mvp.view.MvpView;

public interface Presenter<T extends MvpView> {

    void attachView(T view);

    void detachView();

    void onDestroy();
}
