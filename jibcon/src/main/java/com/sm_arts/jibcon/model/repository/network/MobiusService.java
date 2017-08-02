package com.sm_arts.jibcon.model.repository.network;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by jaeyoung on 7/14/17.
 */

// todo remove this file
public interface MobiusService {
    @POST("/mobius-yt/ae-ledapp/cnt-ledonoff")
    retrofit2.Call<Object> turnOnLed(
            @Header("Accept") String accept,
            @Header("X-M2M-RI") String ri,
            @Header("X-M2M-Origin") String origin,
            @Header("Content-Type") String contentType,
            @Body ApiCinC apiCinC
            );

    class ApiCinC {
        @SerializedName("m2m:cin")
        M2mcin m2mcin;

        public ApiCinC(int con) {
            this.m2mcin = new M2mcin(con);
        }
    }

    class M2mcin {
        int con;

        public M2mcin(int con) {
            this.con = con;
        }
    }
}
