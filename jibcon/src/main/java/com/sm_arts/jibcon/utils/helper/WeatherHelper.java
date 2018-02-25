package com.sm_arts.jibcon.utils.helper;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.WeatherDataDto;
import com.sm_arts.jibcon.data.models.api.dto.routine.DeviceMenuWeatherData;
import com.sm_arts.jibcon.data.repository.network.api.WeatherService;
import com.sm_arts.jibcon.ui.main.devicemenu.fragment.DeviceMenuView;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-11-18.
 */

public class WeatherHelper {
    private static final String TAG = "WeatherHelper";
    private static WeatherHelper obj = null;
    private static DeviceMenuWeatherData deviceMenuWeatherData;

    public static DeviceMenuWeatherData getDeviceMenuWeatherData() {
        return deviceMenuWeatherData;
    }

    public static void setDeviceMenuWeatherData(DeviceMenuWeatherData deviceMenuWeatherData) {
        WeatherHelper.deviceMenuWeatherData = deviceMenuWeatherData;
    }

    public static WeatherHelper getInstance() {
        if (obj == null)
            obj = new WeatherHelper();
        return obj;
    }
    public static void getCurrentWeather(DeviceMenuView mDeviceMenuView) {
        Log.d(TAG, "getCurrentWeather: ");
        WeatherService weatherService = RetrofitClients.getInstance().getService(WeatherService.class);
        // TODO: 2018. 1. 31. 현재 위치 위도경도를 하드코딩값이 아니라 MAP api로 받은 값으로 넣을것
        Call<LinkedTreeMap<String,Object>> c = weatherService.getWeatherData(new WeatherDataDto("37.5639","126.9823","Seoul"));

        c.enqueue(new Callback<LinkedTreeMap<String, Object>>() {
            @Override
            public void onResponse(Call<LinkedTreeMap<String, Object>> call, Response<LinkedTreeMap<String, Object>> response) {

                LinkedTreeMap<String, Object> body = (LinkedTreeMap<String, Object>) response.body();
                ArrayList<LinkedTreeMap<String,Object>> weatherDataList = (ArrayList<LinkedTreeMap<String,Object>>)body.get("data");

                String city = ((String)body.get("city"));
                String skyStatus = (String)((LinkedTreeMap<String,Object>)weatherDataList.get(0).get("sky")).get("name");
                String temperature = (String)((LinkedTreeMap<String,Object>)weatherDataList.get(0).get("temperature")).get("tc");
                String weatherCode = (String)((LinkedTreeMap<String,Object>)weatherDataList.get(0).get("sky")).get("code");

                DeviceMenuWeatherData deviceMenuWeatherData = new DeviceMenuWeatherData();
                deviceMenuWeatherData.location = city;
                deviceMenuWeatherData.temperature =temperature;
                deviceMenuWeatherData.sky = skyStatus;
                deviceMenuWeatherData.weatherCode = weatherCode;
                WeatherHelper.setDeviceMenuWeatherData(deviceMenuWeatherData);
                mDeviceMenuView.setWeatherInfo(deviceMenuWeatherData);


            }

            @Override
            public void onFailure(Call<LinkedTreeMap<String, Object>> call, Throwable t) {

            }
        });

    }
}
