package com.support.android.vkclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.support.android.vkclient.R;
import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.mvp.presenter.UserAccountPresenter;
import com.support.android.vkclient.mvp.view.UserAccountView;
import com.support.android.vkclient.ui.fragment.UserPageFragment;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;

public class UserAccountActivity extends MvpActivity<UserAccountPresenter> implements UserAccountView {

    @Inject
    UserAccountPresenter presenter;

    @Override
    UserAccountPresenter createPresenter() {
        return presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getOnBackPressedDispatcher().addCallback(this, () -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateAccountInfo();
    }

    @Override
    public void onAccountUpdated(UserProfile accountInfo) {
        UserPageFragment fragment = UserPageFragment.newInstance(accountInfo, UserPageFragment.USER_ACCOUNT_FLAG);
        getSupportFragmentManager().beginTransaction().add(R.id.user_profile_fragment_container, fragment).commit();
    }
}
