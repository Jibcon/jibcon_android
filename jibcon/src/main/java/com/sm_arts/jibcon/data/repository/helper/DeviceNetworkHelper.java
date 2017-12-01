package com.sm_arts.jibcon.data.repository.helper;


import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.network.api.DeviceService;
import com.sm_arts.jibcon.utils.housemanager.JibconHouseManager;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 8/9/17.
 */

public class DeviceNetworkHelper {
    private static final String TAG = "DeviceNetworkHelper";
    private static DeviceNetworkHelper sInstance;

    private DeviceService service;

    private DeviceNetworkHelper() {
        service = RetrofitClients.getInstance().getService(DeviceService.class);
    }

    public static DeviceNetworkHelper getInstance() {
        if (sInstance == null) {
            synchronized(DeviceNetworkHelper.class) {
                if (sInstance == null) {
                    sInstance = new DeviceNetworkHelper();
                }
            }
        }
        return sInstance;
    }

    public void getDevices(final Consumer<List<DeviceItem>> finished) {
        //must be deleted
        if(JibconHouseManager.getInstance().getmCurrentHouse() == null)
            return;
        //must be deleted
        Call<List<DeviceItem>> call = service.getDevices(
                JibconHouseManager.getInstance().getmCurrentHouse().house_id);
        call.enqueue(new Callback<List<DeviceItem>>() {
            @Override
            public void onResponse(Call<List<DeviceItem>> call, Response<List<DeviceItem>> response) {
                List<DeviceItem> result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: getDevices failed");
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<DeviceItem>> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void deleteDevice(DeviceItem deviceItem, final Consumer<Void> finished){
        Log.d(TAG, "deleteDevice: ");
        Call<String> call = service.deleteDevice(
                JibconLoginManager.getInstance().getUserId(),
                deviceItem.getId()
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void postDevice(DeviceItem deviceItem, final Consumer<DeviceItem> finished) {
        Call<DeviceItem> call = service.postDevice(
                JibconLoginManager.getInstance().getUserId(),
                deviceItem
        );

        call.enqueue(new Callback<DeviceItem>() {
            @Override
            public void onResponse(Call<DeviceItem> call, Response<DeviceItem> response) {
                DeviceItem result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: postDevice failed with " + deviceItem);
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DeviceItem> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: ");
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void putDevice(DeviceItem deviceItem, final Consumer<DeviceItem> finished) {
        Log.d(TAG, "putDevice: deviceItem.getId()"+deviceItem.getId());
        Call<DeviceItem> call = service.putDevice(
                JibconLoginManager.getInstance().getUserTokenAsHeader(),
                deviceItem.getId(),
                deviceItem
        );
        call.enqueue(new Callback<DeviceItem>() {
            @Override
            public void onResponse(Call<DeviceItem> call, Response<DeviceItem> response) {
                DeviceItem result = null;
                if (response.isSuccessful()) {
                    result = response.body();
                } else {
                    Log.d(TAG, "onResponse() called with: code = [" + response.code() + "]," +
                            " message = [" + response.message() + "]");
                    Log.d(TAG, "onResponse: putDevice failed with " + deviceItem);
                }

                try {
                    finished.accept(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DeviceItem> call, Throwable t) {
                try {
                    finished.accept(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
