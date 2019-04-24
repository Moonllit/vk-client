package com.support.android.vkclient.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.support.android.vkclient.R;
import com.support.android.vkclient.domain.dto.UserProfile;
import com.support.android.vkclient.ui.activity.FriendsSectionActivity;
import com.support.android.vkclient.ui.activity.UserGalleryActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class UserPageFragment extends Fragment {

    private static final String ARG_PROFILE = "ARG_PROFILE";
    private static final String ARG_PROFILE_FLAG = "ARG_PROFILE_FLAG";
    public static final int USER_ACCOUNT_FLAG = 1;
    public static final int FRIEND_FLAG = 2;

    private UserProfile userProfile;
    private int profileFlag;

    public static UserPageFragment newInstance(UserProfile profile, int flag) {
        UserPageFragment fragment = new UserPageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PROFILE, profile);
        args.putInt(ARG_PROFILE_FLAG, flag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userProfile = getArguments().getParcelable(ARG_PROFILE);
            profileFlag = getArguments().getInt(ARG_PROFILE_FLAG);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureUserProfileHeader();
        initProfile();
        configureUserProfileSection();
    }

    @Override
    public void onResume() {
        super.onResume();
        // вызывать презентер для обновления профиля
    }

    private void configureUserProfileHeader() {
        initHeader(R.id.user_profile_header_friends, R.string.profile_header_friends_title, userProfile.getCounters().getFriends());
        initHeader(R.id.user_profile_header_albums, R.string.profile_header_albums_title, userProfile.getCounters().getAlbums());
        initHeader(R.id.user_profile_header_photos, R.string.profile_header_all_photos_title, userProfile.getCounters().getPhotos());
    }

    private void configureUserProfileSection() {
        initSection(R.id.user_profile_section_subscribers, R.string.profile_section_subscribers_title, userProfile.getCounters().getFollowers());
        initSection(R.id.user_profile_section_video, R.string.profile_section_video_title, userProfile.getCounters().getVideos());
        initSection(R.id.user_profile_section_audios, R.string.profile_header_audio_title, userProfile.getCounters().getAudios());
    }

    private void initHeader(@IdRes int headerId, @StringRes int titleText, int value) {
        View header = getActivity().findViewById(headerId);
        TextView title = header.findViewById(R.id.title);
        TextView counter = header.findViewById(R.id.value);
        title.setText(titleText);
        counter.setText(String.valueOf(value));

        if (headerId == R.id.user_profile_header_photos) {
            header.setOnClickListener(view -> startActivity(UserGalleryActivity.getIntent(getActivity(), userProfile.getId())));
        }

        if (profileFlag == USER_ACCOUNT_FLAG && headerId == R.id.user_profile_header_friends) {
            header.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), FriendsSectionActivity.class);
                startActivity(intent);
            });
        }
    }

    private void initSection(@IdRes int sectionId, @StringRes int sectionTitle, int value) {
        ((TextView) getActivity().findViewById(sectionId).findViewById(R.id.section_title)).setText(sectionTitle);
        ((TextView) getActivity().findViewById(sectionId).findViewById(R.id.section_value)).setText(String.valueOf(value));
    }

    private void initProfile() {
        if (userProfile != null) {
            TextView name = getActivity().findViewById(R.id.user_name_tv);
            name.setText(userProfile.getFullName());

            String lastSeenDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(userProfile.getLastSeen().getTime() * 1000));
            String lastSeenTime = new SimpleDateFormat("H:mm", Locale.getDefault()).format(new Date(userProfile.getLastSeen().getTime() * 1000));
            TextView onlineStatus = getActivity().findViewById(R.id.user_online_tv);
            ImageView onlineDot = getActivity().findViewById(R.id.user_online_iv);
            if (userProfile.isOnline()) {
                onlineStatus.setText(R.string.user_online);
                onlineStatus.setVisibility(View.VISIBLE);
                onlineDot.setVisibility(View.VISIBLE);
            } else {
                onlineStatus.setText(String.format("Был(а) в сети %s в %s", lastSeenDate, lastSeenTime));
                onlineStatus.setVisibility(View.VISIBLE);
                onlineDot.setVisibility(View.GONE);
            }

            ImageView avatar = getActivity().findViewById(R.id.user_avatar_iv);
            Glide.with(this)
                    .load(userProfile.getPhotoMaxOrig())
                    .into(avatar);

            TextView gender = getActivity().findViewById(R.id.user_profile_gender);
            switch (userProfile.getSex()) {
                case 1:
                    gender.setText("женский");
                    break;
                case 2:
                    gender.setText("мужской");
                    break;
            }

            if (userProfile.getBdate() != null) {
                TextView birthDateHeader = getActivity().findViewById(R.id.user_profile_birth_date_header);
                birthDateHeader.setVisibility(View.VISIBLE);
                TextView birthDate = getActivity().findViewById(R.id.user_profile_birth_date);
                birthDate.setVisibility(View.VISIBLE);
                birthDate.setText(userProfile.getBdate());
            }
        }
    }
}
