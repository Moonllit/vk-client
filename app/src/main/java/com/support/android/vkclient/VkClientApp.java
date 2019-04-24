package com.support.android.vkclient;

import android.util.Log;

import com.support.android.vkclient.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class VkClientApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(VkClientApp.class.getSimpleName(), "VkClientApp started");
    }
}