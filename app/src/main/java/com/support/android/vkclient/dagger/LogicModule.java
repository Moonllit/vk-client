package com.support.android.vkclient.dagger;

import com.support.android.vkclient.domain.api.VkEndpoint;
import com.support.android.vkclient.domain.logic.VkLogic;
import com.support.android.vkclient.domain.logic.impl.VkLogicImpl;
import com.support.android.vkclient.domain.storage.database.dao.FriendDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LogicModule {

    @Singleton
    @Provides
    public VkLogic provideVKLogic(VkEndpoint endpoint, FriendDao friendDao) {
        return new VkLogicImpl(endpoint, friendDao);
    }
}
