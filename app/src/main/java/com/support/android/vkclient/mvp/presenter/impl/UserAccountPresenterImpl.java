package com.support.android.vkclient.mvp.presenter.impl;

import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.domain.logic.VkLogic;
import com.support.android.vkclient.mvp.AppDisposableObserver;
import com.support.android.vkclient.mvp.presenter.UserAccountPresenter;
import com.support.android.vkclient.mvp.view.UserAccountView;

import javax.inject.Inject;

public class UserAccountPresenterImpl extends PresenterImpl<UserAccountView> implements UserAccountPresenter {

    private VkLogic logic;

    @Inject
    public UserAccountPresenterImpl(VkLogic logic) {
        this.logic = logic;
    }

    @Override
    public void updateAccountInfo() {
        disposables.add(logic.getAccountInfo().subscribeWith(new AppDisposableObserver<UserProfile>() {
            @Override
            public void onNext(UserProfile accountInfo) {
                if (view != null) {
                    view.onAccountUpdated(accountInfo);
                }
            }
        }));
    }
}


