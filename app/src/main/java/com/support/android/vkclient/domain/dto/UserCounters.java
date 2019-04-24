package com.support.android.vkclient.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCounters implements Parcelable {

    @SerializedName("albums")
    @Expose
    private int albums;

    @SerializedName("photos")
    @Expose
    private int photos;

    @SerializedName("friends")
    @Expose
    private int friends;

    @SerializedName("followers")
    @Expose
    private int followers;

    @SerializedName("videos")
    @Expose
    private int videos;

    @SerializedName("audios")
    @Expose
    private int audios;

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public int getAudios() {
        return audios;
    }

    public void setAudios(int audios) {
        this.audios = audios;
    }

    protected UserCounters(Parcel in) {
        albums = in.readInt();
        photos = in.readInt();
        friends = in.readInt();
        followers = in.readInt();
        videos = in.readInt();
        audios = in.readInt();
    }

    public static final Creator<UserCounters> CREATOR = new Creator<UserCounters>() {
        @Override
        public UserCounters createFromParcel(Parcel in) {
            return new UserCounters(in);
        }

        @Override
        public UserCounters[] newArray(int size) {
            return new UserCounters[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(albums);
        dest.writeInt(photos);
        dest.writeInt(friends);
        dest.writeInt(followers);
        dest.writeInt(videos);
        dest.writeInt(audios);
    }
}
