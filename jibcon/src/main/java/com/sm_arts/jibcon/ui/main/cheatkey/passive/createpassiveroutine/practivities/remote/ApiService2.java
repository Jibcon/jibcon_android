package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.remote;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by admin on 2017-11-15.
 */

public interface ApiService2 {

    @POST("/api/timer/getMyTasks")
    Call<List<HashMap<String,Object>>> getMyTasks(@Header("Authorization") String userId);

}
