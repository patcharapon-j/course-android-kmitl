package com.example.patcharaponjoksamut.mylazyinstagram.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.patcharaponjoksamut.mylazyinstagram.API.Api;
import com.example.patcharaponjoksamut.mylazyinstagram.Adapter.PostAdapter;
import com.example.patcharaponjoksamut.mylazyinstagram.Model.UserProfile;
import com.example.patcharaponjoksamut.mylazyinstagram.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("android");
    }

    private void getUserProfile(String name) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.getProfile(name).enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                Log.d("Debug", "onResponse:" + response.body());
                display(response.body());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.d("Debug", "onFailure");
            }
        });
    }

    private void display(UserProfile userProfile) {
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n" + userProfile.getPost());

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + userProfile.getFollower());

        TextView textFollwing = findViewById(R.id.textFollwing);
        textFollwing.setText("Following\n" + userProfile.getFollowing());

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 3));
        PostAdapter adapter = new PostAdapter(this);
        adapter.setData(userProfile.getPosts());
        list.setAdapter(adapter);
    }
}