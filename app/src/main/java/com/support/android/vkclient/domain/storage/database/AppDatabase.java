package com.support.android.vkclient.domain.storage.database;

import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.domain.storage.database.dao.FriendDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserProfile.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "database";

    public abstract FriendDao getFriendDao();
}
