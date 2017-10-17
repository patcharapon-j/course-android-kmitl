package com.example.patcharaponjoksamut.mylazyinstagram.API;

import com.example.patcharaponjoksamut.mylazyinstagram.Model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by patcharaponjoksamut on 14/10/2017 AD.
 */

public interface Api {

    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);

    @FormUrlEncoded
    @POST("/follow")
    Call<UserProfile> postProfile(@Field("user") String user, @Field("isFollow") boolean isFollow);

}