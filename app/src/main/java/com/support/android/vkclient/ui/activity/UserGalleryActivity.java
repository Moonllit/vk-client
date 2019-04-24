package com.support.android.vkclient.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.support.android.vkclient.R;
import com.support.android.vkclient.mvp.presenter.UserGalleryPresenter;
import com.support.android.vkclient.mvp.view.UserGalleryView;
import com.support.android.vkclient.ui.adapters.GalleryAdapter;
import com.support.android.vkclient.ui.widget.GridItemDecoration;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserGalleryActivity extends MvpActivity<UserGalleryPresenter> implements UserGalleryView, GalleryAdapter.GalleryItemClickListener {

    private static final String PROFILE_ID = "PROFILE_ID";

    @Inject
    UserGalleryPresenter presenter;

    private GalleryAdapter adapter = new GalleryAdapter(this);

    @Override
    UserGalleryPresenter createPresenter() {
        return presenter;
    }

    public static Intent getIntent(Activity activity, long userId) {
        Intent intent = new Intent(activity, UserGalleryActivity.class);
        intent.putExtra(PROFILE_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_gallery);

        Toolbar toolbar = findViewById(R.id.gallery_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.profile_header_photos);

        RecyclerView recyclerView = findViewById(R.id.gallery_recyclerview);

        int columnCount = getResources().getInteger(R.integer.photos_grid_column_count);
        recyclerView.setLayoutManager(new GridLayoutManager(this, columnCount));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridItemDecoration(4));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllPhotosUrls(getIntent().getLongExtra(PROFILE_ID, 0));
    }

    @Override
    public void allPhotosUrlsUploaded(List<String> photosUrls) {
        adapter.setData(photosUrls);
    }

    @Override
    public void onItemClick(List<String> photosUrls, int position) {
        startActivity(DetailedPhotoViewActivity.getIntent(this, photosUrls, position));
    }
}
