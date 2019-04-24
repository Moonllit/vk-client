package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoResponse {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("items")
    @Expose
    private List<Photo> photos = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
