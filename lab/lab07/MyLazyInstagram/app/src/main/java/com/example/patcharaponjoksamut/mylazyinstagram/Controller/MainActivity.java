package com.example.patcharaponjoksamut.mylazyinstagram.Controller;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.patcharaponjoksamut.mylazyinstagram.API.Api;
import com.example.patcharaponjoksamut.mylazyinstagram.Adapter.PostAdapter;
import com.example.patcharaponjoksamut.mylazyinstagram.Model.UserProfile;
import com.example.patcharaponjoksamut.mylazyinstagram.R;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private UserProfile userProfile;
    private int currentDisplayMode = 3;
    private AVLoadingIndicatorView avi;
    private View overlayView;
    private Button followBtn;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overlayView = findViewById(R.id.overlay_container);
        avi = findViewById(R.id.avi);
        followBtn = findViewById(R.id.followButton);
        rootView = findViewById(R.id.rootView);

        setupSpinner();
        getUserProfile("android");
    }

    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.account_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void getUserProfile(String name) {
        showActivityIndicator();
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
                userProfile = response.body();
                display(userProfile);
                displayImages(userProfile, currentDisplayMode);
                hideActivityIndicator();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.d("Debug", "onFailure");
                hideActivityIndicator();
            }
        });
    }

    private void display(UserProfile userProfile) {
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText(String.valueOf(userProfile.getPost()));

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText(String.valueOf(userProfile.getFollower()));

        TextView textFollwing = findViewById(R.id.textFollowing);
        textFollwing.setText(String.valueOf(userProfile.getFollowing()));

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);

        if(userProfile.isFollow()) {
            setFollowBtnToFollowed();
        } else {
            setFollowBtnToFollow();
        }
    }

    private void displayImages(UserProfile userProfile, int item) {
        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, item));
        PostAdapter adapter = new PostAdapter(this);
        adapter.setData(userProfile.getPosts());
        list.setAdapter(adapter);
    }

    public void onGridButtonClick(View view) {
        currentDisplayMode = 3;
        displayImages(userProfile, currentDisplayMode);
    }

    public void onListButtonClicked(View view) {
        currentDisplayMode = 1;
        displayImages(userProfile, currentDisplayMode);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        getUserProfile((String) adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void showActivityIndicator() {
        //avi.show();
        rootView.setClickable(false);
        overlayView.bringToFront();
        overlayView.setVisibility(View.VISIBLE);
    }

    private void hideActivityIndicator() {
        //avi.hide();
        rootView.setClickable(true);
        overlayView.bringToFront();
        overlayView.setVisibility(View.INVISIBLE);
    }

    public void onFollowBtnPressed(View view) {
        showActivityIndicator();

        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.postProfile(userProfile.getUser(), !userProfile.isFollow()).enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                Log.d("Debug", "onResponse:" + response.body());
                getUserProfile(userProfile.getUser());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.d("Debug", "onFailure");
                hideActivityIndicator();
            }
        });

    }

    private void setFollowBtnToFollowed() {
        followBtn.setText("Followed");
        followBtn.setTextColor(Color.WHITE);
        followBtn.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.holo_blue_dark), PorterDuff.Mode.MULTIPLY);
    }

    private void setFollowBtnToFollow() {
        followBtn.setText("Follow");
        followBtn.setTextColor(Color.BLACK);
        followBtn.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.MULTIPLY);
    }
}