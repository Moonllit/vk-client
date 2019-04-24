package com.support.android.vkclient.mvp.view;

import com.support.android.vkclient.domain.dto.UserProfile;

import java.util.List;

public interface FriendsSectionView extends MvpView {
    void usersListUploaded(List<UserProfile> userProfiles);

    void userPageUploaded(UserProfile userProfile);
}
