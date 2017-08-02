package com.sm_arts.jibcon.utils.consts;

import com.sm_arts.jibcon.BuildConfig;
import com.sm_arts.jibcon.model.repository.network.UserService;
import com.sm_arts.jibcon.model.repository.network.MobiusService;

import java.util.HashMap;

/**
 * Created by jaeyoung on 7/17/17.
 */

public class UrlUtils {
    private static HashMap<String, String> urls;

    static {
        urls = new HashMap<>();

        String apiUrl = BuildConfig.DEBUG ?
                "http://52.79.142.130/" :
                "http://52.79.142.130/";
        urls.put(UserService.class.getName(), apiUrl);

        String mobiusUrl = BuildConfig.DEBUG ?
                "http://52.78.111.146:7579/" :
                "http://52.78.111.146:7579/";
        urls.put(MobiusService.class.getName(), mobiusUrl);
    }

    public static String getUrlWithClassName(String clsName) {
        return urls.get(clsName);
    }
}
