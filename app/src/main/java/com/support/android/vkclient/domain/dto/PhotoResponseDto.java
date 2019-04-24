package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoResponseDto {

    @SerializedName("response")
    @Expose
    private PhotoResponse response;

    public PhotoResponse getResponse() {
        return response;
    }

    public void setResponse(PhotoResponse response) {
        this.response = response;
    }
}
