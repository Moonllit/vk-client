package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendsResponseDto {

    @SerializedName("response")
    @Expose
    private FriendsResponse friendsResponse;

    public FriendsResponse getFriendsResponse() {
        return friendsResponse;
    }

    public void setFriendsResponse(FriendsResponse friendsResponse) {
        this.friendsResponse = friendsResponse;
    }
}
