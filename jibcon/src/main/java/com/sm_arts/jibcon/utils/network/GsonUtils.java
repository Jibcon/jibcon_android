package com.sm_arts.jibcon.utils.network;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaeyoung on 7/17/17.
 */

public class GsonUtils {
    private static GsonConverterFactory sGsonConverterFactory;

    public static GsonConverterFactory getGsonConverterFactory() {
        if (sGsonConverterFactory == null) {
            sGsonConverterFactory = GsonConverterFactory.create();
        }

        return sGsonConverterFactory;
    }
}
