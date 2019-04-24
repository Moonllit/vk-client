package com.support.android.vkclient.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FriendsResponse implements Parcelable {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("items")
    @Expose
    private List<UserProfile> userProfiles;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    protected FriendsResponse(Parcel in) {
        count = in.readInt();
        userProfiles = new ArrayList<>();
        in.readList(userProfiles, UserProfile.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeList(userProfiles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FriendsResponse> CREATOR = new Creator<FriendsResponse>() {
        @Override
        public FriendsResponse createFromParcel(Parcel in) {
            return new FriendsResponse(in);
        }

        @Override
        public FriendsResponse[] newArray(int size) {
            return new FriendsResponse[size];
        }
    };
}
