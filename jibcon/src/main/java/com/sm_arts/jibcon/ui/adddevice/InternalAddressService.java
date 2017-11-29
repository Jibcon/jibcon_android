package com.sm_arts.jibcon.ui.adddevice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2017-11-12.
 */

public interface InternalAddressService {
    @GET("/api/nupnp")
    Call<List<Hub>> getInternalAddress();
}
