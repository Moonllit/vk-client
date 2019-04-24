package com.support.android.vkclient.dagger;

import android.app.Application;
import android.content.Context;

import com.support.android.vkclient.VkClientApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public Context provideContext(VkClientApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    public Application provideApplication(VkClientApp app) {
        return app;
    }
}
