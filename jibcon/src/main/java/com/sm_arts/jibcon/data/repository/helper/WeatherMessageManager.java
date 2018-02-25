package com.sm_arts.jibcon.data.repository.helper;

import android.os.Handler;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.WeatherDataDto;
import com.sm_arts.jibcon.data.repository.network.api.WeatherService;
import com.sm_arts.jibcon.ui.main.datacontrol.DataControllView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by euijoonjung on 2018. 1. 24..
 */

public class WeatherMessageManager {
    private static final String TAG = WeatherMessageManager.class.getName();
    DataControllView mainView;
    public WeatherMessageManager(DataControllView mainView) {
        this.mainView = mainView;
    }

    public  void getCurrentWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.109.13:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherAPI = retrofit.create(WeatherService.class);
        Call<LinkedTreeMap<String,Object>> c = weatherAPI.getWeatherData(new WeatherDataDto(
                "35.159879",
                "129.131529",
                ""
        ));
        c.enqueue(new Callback<LinkedTreeMap<String, Object>>() {
            @Override
            public void onResponse(Call<LinkedTreeMap<String, Object>> call, Response<LinkedTreeMap<String, Object>> response) {
                Log.d(TAG, "onResponse: ");
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response.body() + "]");
                mainView.drawingWeatherChart(response.body());
                mainView.drawingHumidityChart(response.body());
                mainView.onDownloadFinished();
            }


            @Override
            public void onFailure(Call<LinkedTreeMap<String, Object>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });
    }
}
