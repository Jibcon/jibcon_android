package com.sm_arts.jibcon.utils.helper;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.routine.DeviceMenuWeatherData;
import com.sm_arts.jibcon.data.repository.network.api.WeatherService;
import com.sm_arts.jibcon.ui.main.devicemenu.fragment.DeviceMenuView;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.skplanetx.com/gweather/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<HashMap<String, Object>> c = weatherService.getWeather("0663ab3f-3aa2-35dd-9e37-b6d4e40a3ec3",
                "37.5639",
                "126.9823",
                "1");
        c.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                if(!response.isSuccessful())
                    return;
                LinkedTreeMap<String, Object> body = (LinkedTreeMap<String, Object>) response.body().get("gweather");
                List<LinkedTreeMap<String, Object>> current = (List) body.get("current");
                LinkedTreeMap<String, Object> item = current.get(0);
                LinkedTreeMap<String, Object> skyInfo = (LinkedTreeMap) item.get("sky");
                LinkedTreeMap<String, Object> temperature = (LinkedTreeMap) item.get("temperature");
                LinkedTreeMap<String, Object> location = (LinkedTreeMap) item.get("location");

                DeviceMenuWeatherData deviceMenuWeatherData = new DeviceMenuWeatherData();
                deviceMenuWeatherData.location = (String) location.get("city");
                deviceMenuWeatherData.temperature = (String) temperature.get("tc");
                deviceMenuWeatherData.sky = (String) skyInfo.get("name");
                WeatherHelper.setDeviceMenuWeatherData(deviceMenuWeatherData);
                mDeviceMenuView.setWeatherInfo(deviceMenuWeatherData);

            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });
    }
}
