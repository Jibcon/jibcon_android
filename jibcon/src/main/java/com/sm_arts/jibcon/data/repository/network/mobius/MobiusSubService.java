package com.sm_arts.jibcon.data.repository.network.mobius;

import com.sm_arts.jibcon.data.models.mobius.dto.request.RequestSub;
import com.sm_arts.jibcon.data.models.mobius.dto.request.RequestSubscription;
import com.sm_arts.jibcon.data.models.mobius.dto.response.ResponseSub;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 8/2/17.
 */

public interface MobiusSubService {

    //node server
    @POST("/api/addSub")
    Call<Void> addSub(@Body RequestSubscription requestSubscription);
    @POST("/api/deleteSub")
    Call<Void> deleteSub(@Body RequestSubscription requestSubscription);
    //node server

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

    @DELETE("/{cse}/{device_ae}/{device_cnt}/{sub}")
    Call<ResponseBody> deleteSub(
            @Path("cse") String cse,
            @Path("device_ae") String deviceAe,
            @Path("device_cnt") String deviceCnt,
            @Path("sub") String sub,
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin
    );
}
