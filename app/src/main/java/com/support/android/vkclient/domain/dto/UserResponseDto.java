package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponseDto {

    @SerializedName("response")
    @Expose
    private List<UserProfile> userResponse;

    public List<UserProfile> getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(List<UserProfile> userResponse) {
        this.userResponse = userResponse;
    }
}
