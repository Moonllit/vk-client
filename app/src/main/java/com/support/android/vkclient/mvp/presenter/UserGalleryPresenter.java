package com.support.android.vkclient.mvp.presenter;

import com.support.android.vkclient.mvp.view.UserGalleryView;

public interface UserGalleryPresenter extends Presenter<UserGalleryView> {
    void getAllPhotosUrls(Long userId);
}
