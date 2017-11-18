package com.sm_arts.jibcon.data.repository.network.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 2017-11-18.
 */

public interface WeatherService {
    @GET("current/")
    Call<HashMap<String,Object>> getWeather(
            @Query("appKey") String appKey,
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("version") String version
    );
}
