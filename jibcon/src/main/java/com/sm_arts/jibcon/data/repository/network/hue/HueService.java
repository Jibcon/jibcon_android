package com.sm_arts.jibcon.data.repository.network.hue;

import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionReq;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionRes;
import com.sm_arts.jibcon.data.models.api.dto.hue.Hub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 8/13/17.
 */

public interface HueService {
    @PUT("/api/{auth}/lights/{id}/state")
    Call<List<HueResponse>> putDevice(@Path("auth") String auth,
                                     @Path("id") String id,
                                     @Body HueCi body);
    @GET("/api/nupnp")
    Call<List<Hub>> getInternalAddress();
    @POST("/api")
    Call<List<ConnectionRes>> getHueUserName(@Body ConnectionReq devicetype);
    @GET("/api/{username}/lights")
    Call<HashMap<String,Object>> getLights(@Path("username") String username);
    @PUT("/api/{username}/lights/{ID}/state")
    Call<List<ConnectionRes>> changeBulbState(@Path("username") String username,
                                              @Path("ID") String ID,
                                              @Body ConnectionReq onOffState);
    public class HueCi {
        public boolean on;

        public HueCi(boolean on) {
            this.on = on;
        }
    }

    public class HueResponse {
        public Map<String, Boolean> success;
    }
}
