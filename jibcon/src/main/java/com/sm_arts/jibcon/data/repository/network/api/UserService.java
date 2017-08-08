package com.sm_arts.jibcon.data.repository.network.api;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.api.dto.User;
import com.sm_arts.jibcon.data.models.api.dto.UserInfo;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by admin on 2017-04-09.
 */

public interface UserService {
    @GET("/api/samples/sign_in/")
    retrofit2.Call<User> getSampleUser();

    @POST("/api/social_sign_up_or_in/")
    retrofit2.Call<User> login(@Body UserInfo userInfo);

    @POST("api/social_sign_up_or_in/")
    retrofit2.Call<User> logincheck(@Body UserInfo userInfo);

}
