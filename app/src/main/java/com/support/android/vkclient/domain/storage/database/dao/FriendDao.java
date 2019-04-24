package com.support.android.vkclient.domain.storage.database.dao;

import com.support.android.vkclient.domain.dto.UserProfile;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Observable;

@Dao
public interface FriendDao {

    @Query("SELECT * FROM friends")
    Observable<List<UserProfile>> getAllFriends();

    @Query("SELECT * FROM friends WHERE id in (:ids)")
    Observable<List<UserProfile>> getFriendsById(List<Long> ids);

    @Query("SELECT COUNT (*) FROM friends")
    int howManyEntries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserProfile> friends);
}
