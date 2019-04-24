package com.support.android.vkclient.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastUserSeen implements Parcelable {

    @SerializedName("time")
    @Expose
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    protected LastUserSeen(Parcel in) {
        time = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LastUserSeen> CREATOR = new Creator<LastUserSeen>() {
        @Override
        public LastUserSeen createFromParcel(Parcel in) {
            return new LastUserSeen(in);
        }

        @Override
        public LastUserSeen[] newArray(int size) {
            return new LastUserSeen[size];
        }
    };

}
