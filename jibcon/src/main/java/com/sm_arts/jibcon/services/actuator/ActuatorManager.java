package com.sm_arts.jibcon.services.actuator;

import android.text.TextUtils;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.utils.consts.Configs;

import io.reactivex.functions.Action;

/**
 * Created by jaeyoung on 8/14/17.
 */

public class ActuatorManager {
    private static ActuatorManager sInstance;

    private final String DEVICENAME_HUE = Configs.DEVICES_SUPPORTABLE.DEVICENAME_CHOCIED.get(3);
    private final String ACTUATORTYPE_HUE = "HUE";
    private final String ACTUATORTYPE_MOBIUS = "MOBIUS";

    public static ActuatorManager getInstance() {
        if (sInstance == null) {
            synchronized(ActuatorManager.class) {
                if (sInstance == null) {
                    sInstance = new ActuatorManager();
                }
            }
        }

        return sInstance;
    }

    public void toggleItem(DeviceItem item, Action onSuccess) {
        String type = actuatorType(item);
        if (TextUtils.equals(type, ACTUATORTYPE_MOBIUS)) {
            MobiusActuator.getInstance().toggleItem(item, onSuccess);
        } else if (TextUtils.equals(type, ACTUATORTYPE_HUE)) {
            HueActuator.getInstance().toggleItem(item, onSuccess);
        }
    }

    private String actuatorType(DeviceItem item) {
        String deviceName = item.getDeviceName();
        if (TextUtils.equals(deviceName, DEVICENAME_HUE)) {
            return ACTUATORTYPE_HUE;
        } else {
            return ACTUATORTYPE_MOBIUS;
        }
    }
}
