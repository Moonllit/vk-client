package com.support.android.vkclient.ui.activity;

import android.os.Bundle;

import com.support.android.vkclient.mvp.presenter.Presenter;
import com.support.android.vkclient.mvp.view.MvpView;

import androidx.annotation.Nullable;

public abstract class MvpActivity<P extends Presenter> extends BaseActivity implements MvpView {
    protected P presenter;

    abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
