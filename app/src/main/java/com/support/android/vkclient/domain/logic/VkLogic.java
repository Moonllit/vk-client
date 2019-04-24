package com.support.android.vkclient.domain.logic;

import com.support.android.vkclient.domain.dto.OnlineFriendIds;
import com.support.android.vkclient.domain.dto.UserProfile;

import java.util.List;

import io.reactivex.Observable;

public interface VkLogic {

    Observable<UserProfile> getAccountInfo();

    Observable<List<UserProfile>> getFriends();

    Observable<List<UserProfile>> getOnlineFriends();

    Observable<OnlineFriendIds> getOnlineFriendsIds();

    Observable<List<UserProfile>> getUser(String userId);

    Observable<List<String>> getAllPhotosUrls(Long userId);
}
