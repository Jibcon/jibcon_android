package com.sm_arts.jibcon.device.service.network;

import android.util.Log;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.login.loginmanager.JibconLoginManagerImpl;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

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
    private GlobalApplication mApp;
    private ApiService mApiService;
    private static DeviceNetwork sInstance;
    private boolean mIsWorking = false;
    private List<DeviceNetwork.onSuccessListener> mListeners = new ArrayList<>();

    private DeviceNetworkImpl() {
        mApp = GlobalApplication.getGlobalApplicationContext();
        mApiService = RetrofiClients.getInstance().getService(ApiService.class);
    }

    public static DeviceNetwork getInstance() {
        if (sInstance == null) {
            sInstance = new DeviceNetworkImpl();
        }
        return sInstance;
    }

    public void getDeviceItemsFromServer(DeviceNetwork.onSuccessListener listener) {
        mListeners.add(listener);
        Log.d(TAG, "getDeviceItemsFromServer: Call.enqueue");

        if(mIsWorking) {
            Log.d(TAG, "getDeviceItemsFromServer: isWorking -> just addListener");
        } else {
            mIsWorking = true;

            String token = JibconLoginManagerImpl.getInstance()
                                                .getUserToken();
            Call<List<DeviceItem>> c = mApiService.getDevices("Token " + token);
            try {
                c.enqueue(new Callback<List<DeviceItem>>() {
                    @Override
                    public void onResponse(Call<List<DeviceItem>> call, Response<List<DeviceItem>> response) {
                        if (response.isSuccessful()) {
                            List<DeviceItem> result = response.body();
                            Log.d(TAG, "getDeviceItemsFromServer/onResponse: "
                                    + result.toString());
                            notifyListenersOnSuccessGetDeviceItemsFromServer(result);
                        } else {
                            Log.d(TAG, "getDeviceItemsFromServer/onResponse: " +
                                    "code=[" + response.code() + "] " +
                                    "message=[" + response.message() + "]");
                        }
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
        mIsWorking = false;
    }
}