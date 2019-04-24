package com.support.android.vkclient.domain.logic.impl;

import android.util.Log;

import com.support.android.vkclient.domain.api.VkApi;
import com.support.android.vkclient.domain.api.VkEndpoint;
import com.support.android.vkclient.domain.dto.OnlineFriendIds;
import com.support.android.vkclient.domain.dto.Photo;
import com.support.android.vkclient.domain.dto.PhotoSize;
import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.domain.dto.UserResponseDto;
import com.support.android.vkclient.domain.logic.VkLogic;
import com.support.android.vkclient.domain.storage.database.dao.FriendDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class VkLogicImpl implements VkLogic {
    private final VkApi api;
    private final FriendDao database;

    @Inject
    public VkLogicImpl(VkEndpoint vkEndpoint, FriendDao db) {
        api = vkEndpoint.getApi();
        database = db;
    }

    @Override
    public Observable<UserProfile> getAccountInfo() {
        return api.getAccountInfo("photo_max_orig, bdate, sex, counters, online, last_seen")
                .map(userResponseDto -> userResponseDto.getUserResponse().get(0))
                .doOnError(throwable -> Log.d("getAccountInfo(): ", throwable.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<UserProfile>> getFriends() {
        return api.getFriends("photo_100, domain")
                .map(friendsResponseDto -> friendsResponseDto.getFriendsResponse().getUserProfiles())
                .doOnNext(this::insertDataInBd)
                .onErrorResumeNext(throwable -> {
                    return database.getAllFriends();
                })
                .doOnError(throwable -> Log.d("getFriends(): ", throwable.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<String>> getAllPhotosUrls(Long userId) {
        return api.getAllPhotos(userId, 200)
                .map(photoResponseDto -> {
                    List<Photo> photos = photoResponseDto.getResponse().getPhotos();
                    List<String> urls = new ArrayList<>();

                    for (Photo photo : photos) {
                        List<PhotoSize> sizes = photo.getSizes();

                        for (PhotoSize size : sizes) {
                            if (size.getType().equals("x")) {
                                urls.add(size.getUrl());
                                break;
                            }
                        }
                    }
                    return urls;
                })
                .doOnError(throwable -> Log.d("getAllPhotosUrls(): ", throwable.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<OnlineFriendIds> getOnlineFriendsIds() {
        return api.getOnlineFriends()
                .doOnError(throwable -> Log.d("getOnlineFriendsIds(): ", throwable.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<UserProfile>> getUser(String userId) {
        return api.getUser("photo_100, photo_max_orig, bdate, counters, sex, online, last_seen", userId)
                .map(UserResponseDto::getUserResponse)
                .doOnError(throwable -> Log.d("getUser(): ", throwable.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<UserProfile>> getOnlineFriends() {
        return getOnlineFriendsIds()
                .flatMap((Function<OnlineFriendIds, ObservableSource<List<UserProfile>>>)
                        onlineFriendIds -> database.getFriendsById(onlineFriendIds.getOnlineFriendIds()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void insertDataInBd(List<UserProfile> profiles) {
        database.insertAll(profiles);
    }
}
