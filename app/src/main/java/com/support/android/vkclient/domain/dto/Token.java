package com.support.android.vkclient.domain.dto;

public class Token {

    private final long ownerId;

    private final String accessToken;

    public Token(long ownerId, String accessToken) {
        this.ownerId = ownerId;
        this.accessToken = accessToken;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
