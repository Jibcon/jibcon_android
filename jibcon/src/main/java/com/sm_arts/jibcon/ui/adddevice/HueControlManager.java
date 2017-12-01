package com.sm_arts.jibcon.ui.adddevice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionReq;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionRes;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-11-12.
 */

public class HueControlManager {
    private static HashMap<String, HueBulb> bulbMap;
    public static String internalAddress;
    public static String internalUsername;


    private static final String TAG = HueControlManager.class.getName();
    public static HueControlManager obj = null;

    public static HueControlManager getInstance() {
        if (obj == null) {
            synchronized (HueControlManager.class) {
                if (obj == null) {
                    bulbMap = new HashMap<>();
                    obj = new HueControlManager();
                }

            }
        }
        return obj;
    }


    public static void changeBulbState(LinkedTreeMap<String, Object> data, Boolean onOff) {

        LinkedTreeMap<String,Object> item;
        for (String ID :
                data.keySet()) {
            //ID : 2,3,4,...
            item = (LinkedTreeMap<String,Object>)data.get(ID);
            ConnectionReq connectionReq = new ConnectionReq();
            connectionReq.on = onOff;
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl((String)item.get("internalAddress"))
                    .build();
            Hue_Internal hueInternal = retrofit.create(Hue_Internal.class);
            Call<List<ConnectionRes>> c = hueInternal.changeBulbState((String)item.get("internalUsername"), ID, connectionReq);
            c.enqueue(new Callback<List<ConnectionRes>>() {
                @Override
                public void onResponse(Call<List<ConnectionRes>> call, Response<List<ConnectionRes>> response) {
                    Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                }

                @Override
                public void onFailure(Call<List<ConnectionRes>> call, Throwable t) {
                    Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                }
            });

        }

    }

}
