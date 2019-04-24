package com.support.android.vkclient.mvp.presenter;

import com.support.android.vkclient.mvp.view.UserAccountView;

public interface UserAccountPresenter extends Presenter<UserAccountView> {
    void updateAccountInfo();
}
