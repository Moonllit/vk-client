package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WallResponse {

    @SerializedName("count")
    @Expose
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
