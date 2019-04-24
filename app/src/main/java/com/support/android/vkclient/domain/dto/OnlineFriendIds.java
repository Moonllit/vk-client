package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OnlineFriendIds {

    @SerializedName("response")
    @Expose
    private List<Long> onlineFriendIds = null;

    public List<Long> getOnlineFriendIds() {
        return onlineFriendIds;
    }

    public void setOnlineFriendIds(List<Long> onlineFriendIds) {
        this.onlineFriendIds = onlineFriendIds;
    }

    public int getOnlineFriendIdsNumber() {
        return onlineFriendIds.size();
    }
}
