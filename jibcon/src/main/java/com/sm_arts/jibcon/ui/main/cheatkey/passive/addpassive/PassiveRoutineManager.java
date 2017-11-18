package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.ActionWeatherData;
import com.sm_arts.jibcon.data.models.api.dto.RoutineItem;
import com.sm_arts.jibcon.data.repository.network.api.RoutineService;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-11-19.
 */

public class PassiveRoutineManager {
    private static final String TAG = "PassiveRoutineManager";
    private  static PassiveRoutineManager sObj = null;
    public static PassiveRoutineManager getInstance() {
        if(sObj == null)
            sObj = new PassiveRoutineManager();
        return sObj;
    }

    public static void timeWeater(String mTriggerTime, ActionWeatherData mActionWeatherData) {
        String[] timeArr = mTriggerTime.split(":");
        RoutineService service = RetrofitClients.getInstance().getService(RoutineService.class);
        RoutineItem routineItem = new RoutineItem();
        routineItem.userId = JibconLoginManager.getInstance().getUserId();
        routineItem.hour = timeArr[0];
        routineItem.minute = timeArr[1];
        routineItem.task_type = "weather";
        routineItem.data = new LinkedTreeMap<>();
        routineItem.data.put("lat",mActionWeatherData.lat);
        routineItem.data.put("lon",mActionWeatherData.lon);
        
        Call<Void> c = service.addTask(routineItem);
        c.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
