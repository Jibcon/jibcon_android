package com.sm_arts.jibcon.utils.consts;

import com.sm_arts.jibcon.BuildConfig;
import com.sm_arts.jibcon.data.repository.network.api.DeviceService;
import com.sm_arts.jibcon.data.repository.network.api.HouseService;
import com.sm_arts.jibcon.data.repository.network.api.RoutineService;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.data.repository.network.api.WeatherService;
import com.sm_arts.jibcon.data.repository.network.hue.HueService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusAeService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusCiService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusCntService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusSubService;
import com.sm_arts.jibcon.ui.adddevice.Hue_Internal;
import com.sm_arts.jibcon.ui.adddevice.InternalAddressService;

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

        String nodeApiUrl = BuildConfig.DEBUG ?
                "http://52.79.109.13:8000/" : // 우리 서버 주소로 바꾸기
                "http://52.79.109.13:8000/";
//        String nodeApiUrl = BuildConfig.DEBUG ?
//                "http://smarts.asuscomm.com:8000/" :
//                "http://smarts.asuscomm.com:8000/";

        String InternalServices = "https://www.meethue.com";


        urls.put(InternalAddressService.class.getName(), InternalServices);
        urls.put(Hue_Internal.class.getName(),InternalServices);
        urls.put(UserService.class.getName(), nodeApiUrl);
        urls.put(DeviceService.class.getName(), nodeApiUrl);
        urls.put(RoutineService.class.getName(), nodeApiUrl);
        urls.put(HouseService.class.getName(),nodeApiUrl);
        urls.put(WeatherService.class.getName(),nodeApiUrl);
        String mobiusUrl =
                "http://" + Configs.Mobius.HOST + ":" + Configs.Mobius.PORT + "/";
        urls.put(MobiusCiService.class.getName(), mobiusUrl);
        urls.put(MobiusAeService.class.getName(), mobiusUrl);
        urls.put(MobiusCntService.class.getName(), mobiusUrl);
        urls.put(MobiusSubService.class.getName(), nodeApiUrl);

        String hueUrl =
                "http:/192.168.1.83";
        urls.put(HueService.class.getName(), hueUrl);
    }

    public static void setUrls(String key, String body){urls.put(key,body);}
    public static String getUrlWithClassName(String clsName) {
        return urls.get(clsName);
    }
}
