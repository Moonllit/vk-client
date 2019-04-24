package com.support.android.vkclient.domain.dto;

public class CountersUpdate {

    private final int oldFriendsCounter;
    private final int newFriendsCounter;

    public CountersUpdate(int oldFriendsCounter, int newFriendsCounter) {
        this.oldFriendsCounter = oldFriendsCounter;
        this.newFriendsCounter = newFriendsCounter;
    }

    public int getOldFriendsCounter() {
        return oldFriendsCounter;
    }

    public int getNewFriendsCounter() {
        return newFriendsCounter;
    }
}
