package com.sm_arts.jibcon.ui.main.fragments;

import android.content.Context;
import android.util.Log;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.network.MobiusService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 7/21/17.
 */

class DeviceMenuPresenter {
    private static final String TAG = "DeviceMenuPresenter";
    private final DeviceMenuView mView;

    public DeviceMenuPresenter(DeviceMenuView view) {
        mView = view;
    }

    public void loadData(Consumer<List<DeviceItem>> finished) {
        Log.d(TAG, "loadData: ");
        DeviceServiceImpl.getInstance().getDeviceItems(
                deviceItems -> {
                    Log.d(TAG, "onSuccessGetDeviceItems: deviceItems=" + deviceItems);
                    try {
                        finished.accept(deviceItems);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void deviceItemIvClicked(int position) {
        Log.d(TAG, "deviceItemIvClicked() called with: " +
                "position = [" + position + "]");

        MobiusService service = RetrofiClients.getInstance().getService(MobiusService.class);
        Call<Object> call = service.turnOnLed(
                "application/json",
                "1",
                "/0.1",
                "application/vnd.onem2m-res+json; ty=4",
                new MobiusService.ApiCinC(3)
        );

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], " +
                        "response.code() = [" + response.code() + "]");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void threedotIvClicked(int position) {
        Log.d(TAG, "threedotIvClicked() called with: " +
                "position = [" + position + "]");
        mView.showDeviceDialog();
    }
}
