package com.sm_arts.jibcon.data.repository.network.api;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 8/8/17.
 */

public interface DeviceService {
    @POST("/api/devices/")
    Call<DeviceItem> postDevice(@Header("Authorization") String token,
                               @Body DeviceItem deviceItem);

    @GET("/api/devices/")
    Call<List<DeviceItem>> getDevices(@Header("Authorization")String token);

    @PUT("/api/devices/{id}/")
    Call<DeviceItem> putDevice(@Header("Authorization") String token,
                               @Path("id") String id,
                               @Body DeviceItem deviceItem);
    @DELETE("/api/deleteDevice/{id}")
    Call<String> deleteDevice(@Header("Authorization") String token,
                            @Path("id") String id);
}
