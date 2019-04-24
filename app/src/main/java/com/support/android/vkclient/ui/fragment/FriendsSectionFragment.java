package com.support.android.vkclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.android.vkclient.R;
import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.mvp.presenter.FriendsSectionPresenter;
import com.support.android.vkclient.mvp.view.FriendsSectionView;
import com.support.android.vkclient.ui.activity.UserPageActivity;
import com.support.android.vkclient.ui.adapters.PeopleAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

public class FriendsSectionFragment extends DaggerFragment implements FriendsSectionView, PeopleAdapter.PeopleItemClickListener {

    private static final String ARG_IS_ONLINE = "ARG_IS_ONLINE";

    @Inject
    FriendsSectionPresenter presenter;

    private boolean isOnline;

    private PeopleAdapter adapter = new PeopleAdapter(this);

    public static FriendsSectionFragment newInstance(boolean isOnline) {
        FriendsSectionFragment fragment = new FriendsSectionFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_ONLINE, isOnline);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isOnline = getArguments().getBoolean(ARG_IS_ONLINE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.friends_section_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.getUsersList(isOnline);
    }

    @Override
    public void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    public void usersListUploaded(List<UserProfile> userProfiles) {
        adapter.setData(userProfiles);
    }

    @Override
    public void onItemClick(String userId) {
        presenter.getUserPage(userId);
    }

    @Override
    public void userPageUploaded(UserProfile userProfile) {
        if (userProfile != null) {
            startActivity(UserPageActivity.getIntent(getActivity(), userProfile));
        }
    }
}
