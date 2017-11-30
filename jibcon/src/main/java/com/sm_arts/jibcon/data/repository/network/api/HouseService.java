package com.sm_arts.jibcon.data.repository.network.api;

import com.sm_arts.jibcon.data.models.api.dto.HouseInfo;
import com.sm_arts.jibcon.data.models.api.dto.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 2017-11-30.
 */

public interface HouseService {
    @POST("/api/house/addHouse")
    Call<HouseInfo> addHouse(@Header("Authorization")String user_id, @Body HouseInfo houseInfo);

    @POST("/api/house/getMyHouses")
    Call<List<HouseInfo>> getMyHouses(@Header("Authorization")String user_id);

    @DELETE("/api/house/deleteHouse")
    Call<Void> deleteHouse(@Body String house_id);

    @GET("/api/house/changeCurrentHouse/{newHouse}")
    Call<User> changeCurrentHouse(@Header("Authorization") String user_id,
                                  @Path("newHouse") String house_id);

    @GET("/api/house/getCurrentHouse")
    Call<HouseInfo> getCurrentHouse(@Header("Authorization")String house_id);
}
