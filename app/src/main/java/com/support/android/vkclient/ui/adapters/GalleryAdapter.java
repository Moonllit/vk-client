package com.support.android.vkclient.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.support.android.vkclient.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {

    private final List<String> photosUrls = new ArrayList<>();
    private final GalleryItemClickListener onItemClickListener;

    public interface GalleryItemClickListener {
        void onItemClick(List<String> photosUrls, int position);
    }

    public GalleryAdapter(GalleryItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<String> urls) {
        photosUrls.clear();
        photosUrls.addAll(urls);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_photos, parent, false);
        return new GalleryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, int position) {
        String url = photosUrls.get(position);

        RequestOptions options = new RequestOptions()
                .override(220, 220)
                .centerCrop();

        Glide.with(holder.itemView.getContext())
                .load(url)
                .apply(options)
                .thumbnail(Glide.with(holder.itemView.getContext())
                        .load(R.drawable.ic_no_image)
                        .apply(options))
                .into(holder.photo);

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(photosUrls, position));
    }

    @Override
    public int getItemCount() {
        return photosUrls.size();
    }

    class GalleryHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        GalleryHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.item_gallery_photo_preview);
        }
    }
}
