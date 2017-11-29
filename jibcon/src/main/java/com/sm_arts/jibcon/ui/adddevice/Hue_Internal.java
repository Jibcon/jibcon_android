package com.sm_arts.jibcon.ui.adddevice;


import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionReq;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionRes;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by admin on 2017-11-12.
 */

public interface Hue_Internal {
    @POST("/api")
    Call<List<ConnectionRes>> getHueUserName(@Body ConnectionReq devicetype);
    @GET("/api/{username}/lights")
    Call<HashMap<String,Object>> getLights(@Path("username") String username);
    @PUT("/api/{username}/lights/{ID}/state")
    Call<List<ConnectionRes>> changeBulbState(@Path("username") String username,
                                              @Path("ID") String ID,
                                              @Body ConnectionReq onOffState);
}
