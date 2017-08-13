package com.sm_arts.jibcon.services.actuator;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.helper.MobiusNetworkHelper;
import com.sm_arts.jibcon.utils.consts.MqttTopicUtils;

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

    public void turnItemOn(DeviceItem item) {
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
                    updateItem(item);
                },
                // failed
                () -> {
                    Log.d(TAG, "toggleActivate: onFail postCi");
                }
        );
    }

    private void updateItem(DeviceItem item) {
        DeviceNetworkHelper.getInstance().putDevice(item, deviceItem -> {});
    }
}
