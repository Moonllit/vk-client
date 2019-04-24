package com.support.android.vkclient.ui.activity;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;
import com.support.android.vkclient.R;
import com.support.android.vkclient.ui.fragment.FriendsSectionFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class FriendsSectionActivity extends BaseActivity {

    private final List<FriendsSectionFragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_section);

        Toolbar toolbar = findViewById(R.id.friends_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.profile_header_friends_title);

        fragmentList.add(FriendsSectionFragment.newInstance(false));
        fragmentList.add(FriendsSectionFragment.newInstance(true));
        fragmentTitleList.add("Друзья");
        fragmentTitleList.add("В сети");

        ViewPager viewPager = findViewById(R.id.friends_section_viewpager);
        TabLayout tabLayout = findViewById(R.id.friends_section_tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPager.setAdapter(new FriendsViewPagerAdapter(getSupportFragmentManager()));
    }

    class FriendsViewPagerAdapter extends FragmentStatePagerAdapter {

        public FriendsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
