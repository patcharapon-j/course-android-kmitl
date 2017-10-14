package com.example.patcharaponjoksamut.mylazyinstagram.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.patcharaponjoksamut.mylazyinstagram.Model.PostModel;
import com.example.patcharaponjoksamut.mylazyinstagram.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patcharaponjoksamut on 14/10/2017 AD.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<PostModel> data;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        this.data = new ArrayList<>();
    }

    @Override
    public PostAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(activity).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);  }
    }

    public void setData(List<PostModel> data) {
        this.data = data;
    }
}
