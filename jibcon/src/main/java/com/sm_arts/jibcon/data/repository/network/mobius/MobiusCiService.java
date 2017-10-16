package com.sm_arts.jibcon.data.repository.network.mobius;

import com.sm_arts.jibcon.data.models.mobius.dto.request.RequestCi;
import com.sm_arts.jibcon.data.models.mobius.dto.response.ResponseCi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 7/14/17.
 */

public interface MobiusCiService {
    @POST("/{cse}/{device_ae}/{device_cnt}")
    Call<ResponseCi> postCi(
            @Path("cse") String cse,
            @Path("device_ae") String deviceAe,
            @Path("device_cnt") String deviceCnt,
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin,
            @Header("Content-Type") String contentType,
            @Body RequestCi requestCi
    );

}
