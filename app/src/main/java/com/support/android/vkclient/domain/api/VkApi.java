package com.support.android.vkclient.domain.api;

import com.support.android.vkclient.domain.dto.FriendsResponseDto;
import com.support.android.vkclient.domain.dto.OnlineFriendIds;
import com.support.android.vkclient.domain.dto.PhotoResponseDto;
import com.support.android.vkclient.domain.dto.UserResponseDto;
import com.support.android.vkclient.domain.dto.WallResponseDto;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApi {

    @GET("users.get")
    Observable<UserResponseDto> getAccountInfo(@Query("fields") String fields);

    @GET("friends.get?order=hints")
    Observable<FriendsResponseDto> getFriends(@Query("fields") String fields);

    @GET("friends.getOnline")
    Observable<OnlineFriendIds> getOnlineFriends();

    @GET("users.get")
    Observable<UserResponseDto> getUser(@Query("fields") String fields, @Query("user_ids") String userId);

    @GET("wall.get")
    Observable<WallResponseDto> getWall();

    @GET("photos.getAll")
    Observable<PhotoResponseDto> getAllPhotos(@Query("owner_id") Long userId, @Query("count") int photoCount);
}
