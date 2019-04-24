package com.support.android.vkclient.dagger;

import android.content.Context;

import com.support.android.vkclient.domain.api.VkEndpoint;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    @Singleton
    public VkEndpoint provideVKEndpoint(Context context){
        return new VkEndpoint(context);
    }
}
