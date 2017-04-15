package com.example.admin.jipcon2.network;

import com.example.admin.jipcon2.network.userinfo.User;
import com.example.admin.jipcon2.network.userinfo.UserInfo;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by admin on 2017-04-09.
 */

public interface ApiService {
    public static final String API_URL = "http://52.79.181.148/";


    @POST("/api/social_sign_up_or_in/")
    retrofit2.Call<User> login(@Body UserInfo userInfo);


}
