package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.remote;

/**
 * Created by woojinkim on 2017. 10. 24..
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://13.229.127.34:3000/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}