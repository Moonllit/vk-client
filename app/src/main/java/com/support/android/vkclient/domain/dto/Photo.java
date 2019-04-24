package com.support.android.vkclient.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sizes")
    @Expose
    private List<PhotoSize> sizes = null;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("date")
    @Expose
    private int date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PhotoSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<PhotoSize> sizes) {
        this.sizes = sizes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
