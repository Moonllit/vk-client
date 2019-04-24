package com.support.android.vkclient.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.support.android.vkclient.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString("vk_token", null) == null) {
            setContentView(R.layout.activity_main);
            findViewById(R.id.login_btn).setOnClickListener(v -> loginToAccount());
        } else {
            Intent intent = new Intent(this, UserAccountActivity.class);
            startActivity(intent);
        }
    }

    public void loginToAccount() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }
}
