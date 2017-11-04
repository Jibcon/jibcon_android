package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.remote;

/**
 * Created by woojinkim on 2017. 10. 24..
 */

import com.sm_arts.jibcon.data.models.api.dto.NotiData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {


    @POST("/users/post/prinfo")
    @FormUrlEncoded
    Call<NotiData> saveNotidata(@Field("time") String time,
                                @Field("token") String token,
                                @Field("message") String message);

}