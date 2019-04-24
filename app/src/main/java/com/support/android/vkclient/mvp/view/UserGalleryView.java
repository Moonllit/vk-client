package com.support.android.vkclient.mvp.view;

import java.util.List;

public interface UserGalleryView extends MvpView {
    void allPhotosUrlsUploaded(List<String> photosUrls);
}
