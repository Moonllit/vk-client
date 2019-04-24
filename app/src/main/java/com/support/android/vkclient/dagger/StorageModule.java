package com.support.android.vkclient.dagger;

import android.content.Context;

import com.support.android.vkclient.domain.storage.database.AppDatabase;
import com.support.android.vkclient.domain.storage.database.dao.FriendDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, AppDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public FriendDao provideFriendDao(AppDatabase database) {
        return database.getFriendDao();
    }
}
