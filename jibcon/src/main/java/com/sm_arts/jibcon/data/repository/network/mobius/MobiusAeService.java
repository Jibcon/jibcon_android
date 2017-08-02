package com.sm_arts.jibcon.data.repository.network.mobius;

import com.google.gson.annotations.SerializedName;
import com.sm_arts.jibcon.data.models.mobius.dto.RequestAe;
import com.sm_arts.jibcon.data.models.mobius.dto.ResponseAe;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 8/2/17.
 */

public interface MobiusAeService {
    @POST("/{cse}")
    Call<ResponseAe> postAe(
            @Path("cse") String cse,
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin,
            @Header("Content-Type") String contentType,
            @Body RequestAe requestAe
    );

    @GET("/{cse}/{ae}")
    Call<ResponseAe> getAe(
            @Path("cse") String cse,
            @Path("ae") String ae,
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin
    );
}
