package com.sm_arts.jibcon.utils.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaeyoung on 7/17/17.
 */

public class GsonUtils {
    private static Gson sGson;

    public static Gson getGson() {
        if (sGson == null) {
            sGson = new GsonBuilder()
                    .create();
        }

        return sGson;
    }
}
