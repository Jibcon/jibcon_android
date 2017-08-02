package com.sm_arts.jibcon.ui.main.fragments;

import android.util.Log;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.model.repository.network.MobiusService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import java.util.List;

import io.reactivex.functions.Action;
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
        Log.d(TAG, "DeviceMenuPresenter: ");
        mView = view;
    }

    //region Presenter role

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

        activateDevice(position);
    }

    public void threedotIvClicked(int position) {
        Log.d(TAG, "threedotIvClicked() called with: " +
                "position = [" + position + "]");
        mView.showDeviceDialog();
    }

    public void swipeRefreshed() {
        Log.d(TAG, "swipeRefreshed: ");
        reloadData(mView::setSwiperefreshingOff);
    }

    public void fabDeviceBehindBtnClicked() {
        mView.gotoFloatingButtonDeviceActivity();
    }

    //endregion

    //region Calling Model Layer

    private void reloadData(Action finished) {
        DeviceServiceImpl.getInstance().reloadDeviceItems(
                (deviceItems) -> {
                    mView.refreshDeviceItems(deviceItems);
                    try {
                        finished.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void activateDevice(int position) {
        Log.d(TAG, "activateDevice() called with: position = [" + position + "]");

        // TODO: 7/21/17 replace this stub
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

    //endregion
}
