package com.support.android.vkclient.mvp.view;

import com.support.android.vkclient.domain.dto.UserProfile;

public interface UserAccountView extends MvpView {
    void onAccountUpdated(UserProfile accountInfo);
}
