package com.support.android.vkclient.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.support.android.vkclient.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DetailedPhotoViewActivity extends AppCompatActivity {

    private static final String ARG_PHOTO_URLS = "ARG_PHOTO_URLS";
    private static final String ARG_PHOTO_POSITION = "ARG_PHOTO_POSITION";

    public static Intent getIntent(Activity activity, List<String> photosUrls, int position) {
        Intent intent = new Intent(activity, DetailedPhotoViewActivity.class);
        intent.putStringArrayListExtra(ARG_PHOTO_URLS, (ArrayList<String>) photosUrls);
        intent.putExtra(ARG_PHOTO_POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_photo_view);
        ViewPager viewPager = findViewById(R.id.detailed_photo_viewpager);
        viewPager.setAdapter(new DetailedPhotoViewPagerAdapter(getIntent().getStringArrayListExtra(ARG_PHOTO_URLS)));
        viewPager.setCurrentItem(getIntent().getIntExtra(ARG_PHOTO_POSITION, 0));
    }

    class DetailedPhotoViewPagerAdapter extends PagerAdapter {

        private final List<String> photosUrls;

        DetailedPhotoViewPagerAdapter(List<String> photosUrls) {
            this.photosUrls = photosUrls;
        }

        @Override
        public int getCount() {
            return photosUrls.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_detailed_photo, container, false);

            ImageView photo = view.findViewById(R.id.item_detailed_photo_iv);
            Glide.with(container.getContext())
                    .load(photosUrls.get(position))
                    .into(photo);

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
