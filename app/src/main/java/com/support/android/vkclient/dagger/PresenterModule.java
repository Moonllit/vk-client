package com.support.android.vkclient.dagger;

import com.support.android.vkclient.domain.logic.VkLogic;
import com.support.android.vkclient.mvp.presenter.FriendsSectionPresenter;
import com.support.android.vkclient.mvp.presenter.UserAccountPresenter;
import com.support.android.vkclient.mvp.presenter.UserGalleryPresenter;
import com.support.android.vkclient.mvp.presenter.impl.FriendsSectionPresenterImpl;
import com.support.android.vkclient.mvp.presenter.impl.UserAccountPresenterImpl;
import com.support.android.vkclient.mvp.presenter.impl.UserGalleryPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Singleton
    @Provides
    public UserAccountPresenter provideUserAccountPresenter(VkLogic logic) {
        return new UserAccountPresenterImpl(logic);
    }

    @Provides
    public FriendsSectionPresenter provideFriendsSectionPresenter(VkLogic logic) {
        return new FriendsSectionPresenterImpl(logic);
    }

    @Singleton
    @Provides
    public UserGalleryPresenter provideUserGalleryPresenter(VkLogic logic) {
        return new UserGalleryPresenterImpl(logic);
    }
}
