package com.support.android.vkclient.mvp.presenter;

import com.support.android.vkclient.mvp.view.FriendsSectionView;

public interface FriendsSectionPresenter extends Presenter<FriendsSectionView> {
    void getUsersList(boolean isOnline);
    void getUserPage(String userId);
}
