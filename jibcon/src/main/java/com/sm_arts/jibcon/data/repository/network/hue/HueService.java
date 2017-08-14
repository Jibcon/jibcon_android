package com.sm_arts.jibcon.data.repository.network.hue;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
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
