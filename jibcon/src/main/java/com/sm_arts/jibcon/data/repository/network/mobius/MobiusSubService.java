package com.sm_arts.jibcon.data.repository.network.mobius;

import com.sm_arts.jibcon.data.models.mobius.dto.RequestSub;
import com.sm_arts.jibcon.data.models.mobius.dto.ResponseSub;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 8/2/17.
 */

public interface MobiusSubService {
    @POST("/{cse}/{device_ae}/{device_cnt}")
    Call<ResponseSub> postSub(
            @Path("cse") String cse,
            @Path("device_ae") String deviceAe,
            @Path("device_cnt") String deviceCnt,
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin,
            @Header("Content-Type") String contentType,
            @Body RequestSub requestSub
    );

    @GET("/{cse}/{device_ae}/{device_cnt}/{sub}")
    Call<ResponseSub> getSub(
            @Path("cse") String cse,
            @Path("device_ae") String deviceAe,
            @Path("device_cnt") String deviceCnt,
            @Path("sub") String sub,
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin
    );
}
