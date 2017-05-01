package com.sm_arts.jibcon.Device.service.network;

import android.util.Log;

import com.sm_arts.jibcon.Device.DeviceItem;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.network.repo;

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
    private boolean isWorking = false;
    private List<DeviceNetwork.onSuccessListener> mListeners = new ArrayList<>();

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
        mListeners.add(listener);
        Log.d(TAG, "getDeviceItemsFromServer: Call.enqueue");

        if(isWorking) {
            Log.d(TAG, "getDeviceItemsFromServer: isWorking -> just addListener");
        } else {
            isWorking = true;
            Call<List<DeviceItem>> c = repo.getStaticService().getDevices("Token " + app.getUserToken());
            try {
                c.enqueue(new Callback<List<DeviceItem>>() {
                    @Override
                    public void onResponse(Call<List<DeviceItem>> call, Response<List<DeviceItem>> response) {
                        List<DeviceItem> result = response.body();
                        Log.d(TAG, "getDeviceItemsFromServer/onResponse: " + result.toString());
                        notifyListenersOnSuccessGetDeviceItemsFromServer(result);
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

    private void notifyListenersOnSuccessGetDeviceItemsFromServer(List<DeviceItem> result) {
        Log.d(TAG, "notifyListenersOnSuccessGetDeviceItemsFromServer: ");
        for (DeviceNetwork.onSuccessListener listener:
             mListeners) {
            listener.onSuccessGetDeviceItemsFromServer(result);
        }

        mListeners.clear();
        isWorking = false;
    }


}
