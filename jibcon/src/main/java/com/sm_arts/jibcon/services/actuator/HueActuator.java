package com.sm_arts.jibcon.services.actuator;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.network.hue.HueService;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.List;

import io.reactivex.functions.Action;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class HueActuator {
    private static final String TAG = "HueActuator";

    private static HueActuator sInstance;
    private final String AUTH = "SA-eGHHU8QKG51xo-k3xUlUqigKTlybl8YI7OIEC";

    public static HueActuator getInstance() {
        if (sInstance == null) {
            synchronized(HueActuator.class) {
                if (sInstance == null) {
                    sInstance = new HueActuator();
                }
            }
        }
        return sInstance;
    }

    private HueActuator() {
    }

    public void turnOff(Action onSuccess) {
        Log.d(TAG, "turnOff: ");
        HueService service = RetrofitClients.getInstance().getService(HueService.class);
        Call<List<HueService.HueResponse>> c = service.putDevice(
                AUTH,
                "1",
                new HueService.HueCi(false)
        );
        c.enqueue(new Callback<List<HueService.HueResponse>>() {
            @Override
            public void onResponse(Call<List<HueService.HueResponse>> call, Response<List<HueService.HueResponse>> response) {
                Log.d(TAG, "onResponse: ");
                try {
                    onSuccess.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<HueService.HueResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void turnOn(Action onSuccess) {
        Log.d(TAG, "turnOn: ");
        HueService service = RetrofitClients.getInstance().getService(HueService.class);
        Call<List<HueService.HueResponse>> c = service.putDevice(
                AUTH,
                "1",
                new HueService.HueCi(true)
        );

        c.enqueue(new Callback<List<HueService.HueResponse>>() {
            @Override
            public void onResponse(Call<List<HueService.HueResponse>> call, Response<List<HueService.HueResponse>> response) {
                Log.d(TAG, "onResponse: ");
                try {
                    onSuccess.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<HueService.HueResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void toggleItem(DeviceItem item, Action onSuccess) {
        if (!item.isDeviceOnOffState()) {
            turnOn(
                    () -> {
                        item.setDeviceOnOffState(!item.isDeviceOnOffState());
                        DeviceNetworkHelper.getInstance().putDevice(item, deviceItem -> onSuccess.run());
                    }
            );
        } else {
            turnOff(
                    () -> {
                        item.setDeviceOnOffState(!item.isDeviceOnOffState());
                        DeviceNetworkHelper.getInstance().putDevice(item, deviceItem -> onSuccess.run());
                    }
            );
        }
    }
}
