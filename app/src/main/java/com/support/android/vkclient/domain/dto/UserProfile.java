package com.support.android.vkclient.domain.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "friends")
public class UserProfile implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    @Expose
    private String firstName;

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    @Expose
    private String lastName;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @Ignore
    @SerializedName("sex")
    @Expose
    private int sex;

    @SerializedName("domain")
    @Expose
    private String domain;

    @Ignore
    @SerializedName("bdate")
    @Expose
    private String bdate;

    @ColumnInfo(name = "photo_100")
    @SerializedName("photo_100")
    @Expose
    private String photo100;

    @Ignore
    @SerializedName("photo_200")
    @Expose
    private String photo200;

    @Ignore
    @SerializedName("photo_max")
    @Expose
    private String photoMax;

    @ColumnInfo(name = "photo_max_orig")
    @SerializedName("photo_max_orig")
    @Expose
    private String photoMaxOrig;

    @SerializedName("online")
    @Expose
    private int online;

    @Ignore
    @SerializedName("counters")
    @Expose
    private UserCounters counters;

    @Ignore
    @SerializedName("last_seen")
    @Expose
    private LastUserSeen lastSeen;

    public UserProfile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        fullName = firstName + " " + lastName;
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }

    public String getPhotoMax() {
        return photoMax;
    }

    public void setPhotoMax(String photoMax) {
        this.photoMax = photoMax;
    }

    public String getPhotoMaxOrig() {
        return photoMaxOrig;
    }

    public void setPhotoMaxOrig(String photoMaxOrig) {
        this.photoMaxOrig = photoMaxOrig;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOnline() {
        return online;
    }

    public boolean isOnline() {
        boolean status = false;
        if (online == 1) {
            status = true;
        }
        return status;
    }

    public UserCounters getCounters() {
        return counters;
    }

    public void setCounters(UserCounters counters) {
        this.counters = counters;
    }

    public LastUserSeen getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LastUserSeen lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Ignore
    protected UserProfile(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        fullName = in.readString();
        sex = in.readInt();
        domain = in.readString();
        bdate = in.readString();
        photo100 = in.readString();
        photo200 = in.readString();
        photoMax = in.readString();
        photoMaxOrig = in.readString();
        online = in.readInt();
        counters = in.readParcelable(CountersUpdate.class.getClassLoader());
        lastSeen = in.readParcelable(LastUserSeen.class.getClassLoader());
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(fullName);
        dest.writeInt(sex);
        dest.writeString(domain);
        dest.writeString(bdate);
        dest.writeString(photo100);
        dest.writeString(photo200);
        dest.writeString(photoMax);
        dest.writeString(photoMaxOrig);
        dest.writeInt(online);
        dest.writeParcelable(counters, 0);
        dest.writeParcelable(lastSeen, 0);
    }
}
