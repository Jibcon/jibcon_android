package com.sm_arts.jibcon.services.actuator;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.helper.MobiusNetworkHelper;
import com.sm_arts.jibcon.utils.consts.MqttTopicUtils;

import io.reactivex.functions.Action;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class MobiusActuator {
    private static final String TAG = "MobiusActuator";
    private static MobiusActuator sInstance;

    public static MobiusActuator getInstance() {
        if (sInstance == null) {
            synchronized(MobiusActuator.class) {
                if (sInstance == null) {
                    sInstance = new MobiusActuator();
                }
            }
        }

        return sInstance;
    }

    public void toggleItem(DeviceItem item, Action onSuccess) {
        String con;

        if (!item.isDeviceOnOffState()) {
            con = "on";
        } else {
            con = "off";
        }

        MobiusNetworkHelper.getInstance().createCi(
                item.getAeName(),
                MqttTopicUtils.getRequestCnt(item.getCntName()),
                con,
                // success
                (responseCi) -> {
                    Log.d(TAG, "toggleActivate: onSuccess postCi");
                    item.setDeviceOnOffState(!item.isDeviceOnOffState());
                    DeviceNetworkHelper.getInstance().putDevice(item, deviceItem -> onSuccess.run());
                },
                // failed
                () -> {
                    Log.d(TAG, "toggleActivate: onFail postCi");
                }
        );
    }
}
