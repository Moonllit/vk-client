package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WallResponseDto {

    @SerializedName("response")
    @Expose
    private WallResponse wallResponse;

    public WallResponse getWallResponse() {
        return wallResponse;
    }

    public void setWallResponse(WallResponse response) {
        this.wallResponse = response;
    }
}
