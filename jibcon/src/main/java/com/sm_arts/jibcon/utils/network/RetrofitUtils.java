package com.sm_arts.jibcon.utils.network;

import android.util.Log;

import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.network.MobiusService;
import com.sm_arts.jibcon.utils.consts.UrlUtils;
import com.sm_arts.jibcon.utils.network.GsonUtils;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-04-10.
 */

public class RetrofitUtils {
    private static RetrofitUtils sInstance;

    private HashMap<Class, Object> services = new HashMap<>();

    public static RetrofitUtils getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitUtils();
        }

        return sInstance;
    }

    public Object getService(Class type) {
        Object service = services.get(type);
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(UrlUtils.getUrlWithClassName(type.getName()))
                    .addConverterFactory(GsonUtils.getGsonConverterFactory())
                    .build();

            service = client.create(type);
            services.put(type, service);
        }

        return service;
    }
}
