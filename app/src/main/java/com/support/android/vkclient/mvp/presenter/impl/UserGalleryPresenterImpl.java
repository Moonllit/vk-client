package com.support.android.vkclient.mvp.presenter.impl;

import com.support.android.vkclient.domain.logic.VkLogic;
import com.support.android.vkclient.mvp.AppDisposableObserver;
import com.support.android.vkclient.mvp.presenter.UserGalleryPresenter;
import com.support.android.vkclient.mvp.view.UserGalleryView;

import java.util.List;

import javax.inject.Inject;

public class UserGalleryPresenterImpl extends PresenterImpl<UserGalleryView> implements UserGalleryPresenter {

    private VkLogic logic;

    @Inject
    public UserGalleryPresenterImpl(VkLogic logic) {
        this.logic = logic;
    }

    @Override
    public void getAllPhotosUrls(Long userId) {
        disposables.add(logic.getAllPhotosUrls(userId).subscribeWith(new AppDisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> photosUrls) {
                if (view != null) {
                    view.allPhotosUrlsUploaded(photosUrls);
                }
            }
        }));
    }
}
