package com.sm_arts.jibcon.utils.network;

import com.sm_arts.jibcon.utils.consts.UrlUtils;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-04-10.
 */

public class RetrofiClients {
    private static RetrofiClients sInstance;

    private HashMap<Class, Object> services = new HashMap<>();

    public static RetrofiClients getInstance() {
        if (sInstance == null) {
            synchronized(RetrofiClients.class) {
                if (sInstance == null) {
                    sInstance = new RetrofiClients();
                }
            }
        }
        return sInstance;
    }

    public <T> T getService(Class<? extends T> type) {
        T service = (T) services.get(type);
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(UrlUtils.getUrlWithClassName(type.getName()))

                    // mentoring
                    .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
                    .build();

            service = client.create(type);
            services.put(type, service);
        }

        return service;
    }
}
