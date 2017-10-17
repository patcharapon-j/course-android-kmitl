package com.example.patcharaponjoksamut.mylazyinstagram.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.patcharaponjoksamut.mylazyinstagram.Model.PostModel;
import com.example.patcharaponjoksamut.mylazyinstagram.Model.UserProfile;
import com.example.patcharaponjoksamut.mylazyinstagram.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by patcharaponjoksamut on 14/10/2017 AD.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<PostModel> data;
    private UserProfile userProfile;
    private int item;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        this.data = new ArrayList<>();
    }

    @Override
    public PostAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (this.item == 1) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        }

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(activity).load(imageUrl).into(holder.imageView);

        if (this.item == 1) {
            Glide.with(activity).load(this.userProfile.getUrlProfile()).into(holder.imgProfile);
            holder.username.setText(this.userProfile.getUser());
        }

        holder.likedText.setText(String.valueOf(this.data.get(position).getLike()));
        holder.commentText.setText(String.valueOf(this.data.get(position).getComment()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CircleImageView imgProfile;
        TextView username, likedText, commentText;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            username = itemView.findViewById(R.id.username);
            likedText = itemView.findViewById(R.id.likedText);
            commentText = itemView.findViewById(R.id.commentText);
        }
    }

    public void setData(List<PostModel> data) {
        this.data = data;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
