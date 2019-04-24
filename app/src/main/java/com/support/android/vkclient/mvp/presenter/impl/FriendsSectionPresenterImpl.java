package com.support.android.vkclient.mvp.presenter.impl;

import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.domain.logic.VkLogic;
import com.support.android.vkclient.mvp.AppDisposableObserver;
import com.support.android.vkclient.mvp.presenter.FriendsSectionPresenter;
import com.support.android.vkclient.mvp.view.FriendsSectionView;

import java.util.List;

import javax.inject.Inject;

public class FriendsSectionPresenterImpl extends PresenterImpl<FriendsSectionView> implements FriendsSectionPresenter {

    private VkLogic logic;

    @Inject
    public FriendsSectionPresenterImpl(VkLogic logic) {
        this.logic = logic;
    }

    @Override
    public void getUsersList(boolean isOnline) {
        if (!isOnline) {
            disposables.add(logic.getFriends().subscribeWith(new AppDisposableObserver<List<UserProfile>>() {
                @Override
                public void onNext(List<UserProfile> userProfiles) {
                    if (view != null) {
                        view.usersListUploaded(userProfiles);
                    }
                }
            }));
        } else {
            disposables.add(logic.getOnlineFriends().subscribeWith(new AppDisposableObserver<List<UserProfile>>() {
                @Override
                public void onNext(List<UserProfile> userProfiles) {
                    if (view != null) {
                        view.usersListUploaded(userProfiles);
                    }
                }
            }));
        }
    }

    @Override
    public void getUserPage(String userId) {
        disposables.add(logic.getUser(userId).subscribeWith(new AppDisposableObserver<List<UserProfile>>() {
            @Override
            public void onNext(List<UserProfile> userProfiles) {
                if (view != null) {
                    if (!userProfiles.isEmpty()) {
                        view.userPageUploaded(userProfiles.get(0));
                    }
                }
            }
        }));
    }
}
