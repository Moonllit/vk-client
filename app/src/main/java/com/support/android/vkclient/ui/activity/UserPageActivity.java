package com.support.android.vkclient.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.support.android.vkclient.R;
import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.ui.fragment.UserPageFragment;

import androidx.appcompat.widget.Toolbar;

public class UserPageActivity extends BaseActivity {

    private static final String ARG_USER_PROFILE = "ARG_USER_PROFILE";

    public static Intent getIntent(Activity activity, UserProfile userProfile) {
        Intent intent = new Intent(activity, UserPageActivity.class);
        intent.putExtra(ARG_USER_PROFILE, userProfile);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        Toolbar toolbar = findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        UserPageFragment fragment = UserPageFragment.newInstance(
                getIntent().getParcelableExtra(ARG_USER_PROFILE), UserPageFragment.FRIEND_FLAG);
        getSupportFragmentManager().beginTransaction().add(R.id.user_page_fragment_container, fragment).commit();
    }
}
