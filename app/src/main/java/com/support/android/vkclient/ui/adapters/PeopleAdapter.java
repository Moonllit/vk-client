package com.support.android.vkclient.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.support.android.vkclient.R;
import com.support.android.vkclient.domain.dto.UserProfile;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleHolder> {

    private final List<UserProfile> data = new ArrayList<>();
    private final PeopleItemClickListener onItemClickListener;

    public interface PeopleItemClickListener {
        void onItemClick(String userId);
    }

    public PeopleAdapter(PeopleItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(@NonNull List<UserProfile> userProfiles) {
        data.clear();
        data.addAll(userProfiles);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeopleAdapter.PeopleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_people, viewGroup, false);
        return new PeopleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.PeopleHolder holder, int position) {
        UserProfile user = data.get(position);
        holder.name.setText(user.getFullName());

        holder.online.setVisibility(user.isOnline() ? View.VISIBLE : View.GONE);

        String avaUrl = user.getPhoto100();
        RequestOptions options = new RequestOptions()
                .circleCrop();
        Glide.with(holder.itemView.getContext())
                .load(avaUrl)
                .apply(options)
                .thumbnail(Glide.with(holder.itemView.getContext())
                        .load(R.drawable.ic_no_image)
                        .apply(options))
                .into(holder.avatar);

        holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(user.getDomain()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PeopleHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView avatar;
        ImageView online;

        PeopleHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_people_name);
            avatar = itemView.findViewById(R.id.item_people_avatar);
            online = itemView.findViewById(R.id.item_people_online);
        }
    }
}
