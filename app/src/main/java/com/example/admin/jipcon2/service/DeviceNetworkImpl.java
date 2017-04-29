package com.example.admin.jipcon2.service;

import android.util.Log;
import android.widget.Toast;

import com.example.admin.jipcon2.Device.DeviceItem;
import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.network.ApiService;
import com.example.admin.jipcon2.network.repo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class DeviceNetworkImpl implements DeviceNetwork {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private GlobalApplication app;
    private ApiService apiService;
    private static DeviceNetwork mInstance;

    private DeviceNetworkImpl() {
        app = GlobalApplication.getGlobalApplicationContext();
        apiService = new repo().getService();
    }


    public static DeviceNetwork getInstance() {
        if (mInstance == null){
            mInstance = new DeviceNetworkImpl();
        }
        return mInstance;
    }

    public void getDeviceItemsFromServer(DeviceNetwork.onSuccessListener listener) {
        final DeviceNetwork.onSuccessListener callbackListener = listener;
        Log.d(TAG, "getDeviceItemsFromServer: Call.enqueue");
        Call<List<DeviceItem>> c = repo.getStaticService().getDevices("Token " + app.getUserToken());
        try {
            c.enqueue(new Callback<List<DeviceItem>>() {
                @Override
                public void onResponse(Call<List<DeviceItem>> call, Response<List<DeviceItem>> response) {
                    List<DeviceItem> result = response.body();
                    Log.d(TAG, "getDeviceItemsFromServer/onResponse: "+result.toString());
                    callbackListener.onSuccessGetDeviceItemsFromServer(result);
                }

                @Override
                public void onFailure(Call<List<DeviceItem>> call, Throwable t) {
                    Log.d(TAG, "getDeviceItemsFromServer/onFailure: ");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
