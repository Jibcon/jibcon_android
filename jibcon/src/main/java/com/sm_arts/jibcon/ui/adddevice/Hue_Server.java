package com.sm_arts.jibcon.ui.adddevice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by admin on 2017-11-25.
 */

public interface Hue_Server {
    @POST("/api/hueapi/lights")
    Call<Void> sendLights(@Body List<HueBulb> bulbs);
}
