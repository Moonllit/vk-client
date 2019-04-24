package com.support.android.vkclient.dagger;

import com.support.android.vkclient.ui.activity.FriendsSectionActivity;
import com.support.android.vkclient.ui.activity.MainActivity;
import com.support.android.vkclient.ui.activity.UserAccountActivity;
import com.support.android.vkclient.ui.activity.UserGalleryActivity;
import com.support.android.vkclient.ui.activity.UserPageActivity;
import com.support.android.vkclient.ui.fragment.FriendsSectionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class AndroidModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract UserAccountActivity userAccountActivity();

    @ContributesAndroidInjector
    abstract FriendsSectionActivity friendsSectionActivity();

    @ContributesAndroidInjector
    abstract FriendsSectionFragment friendsSectionFragment();

    @ContributesAndroidInjector
    abstract UserPageActivity userPageActivity();

    @ContributesAndroidInjector
    abstract UserGalleryActivity userGalleryActivity();
}
