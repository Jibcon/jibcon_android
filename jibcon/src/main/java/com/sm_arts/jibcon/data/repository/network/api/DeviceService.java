package com.sm_arts.jibcon.data.repository.network.api;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by jaeyoung on 8/8/17.
 */

public interface DeviceService {
    @POST("/api/devices/")
    Call<DeviceItem> addDevice(@Header("Authorization") String token,
                               @Body DeviceItem deviceItem);

    @GET("/api/devices/")
    Call<List<DeviceItem>> getDevices(@Header("Authorization")String token);
}
