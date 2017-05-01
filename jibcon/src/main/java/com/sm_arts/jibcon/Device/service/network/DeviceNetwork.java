package com.sm_arts.jibcon.Device.service.network;

import com.sm_arts.jibcon.Device.DeviceItem;

import java.util.List;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public interface DeviceNetwork {
    void getDeviceItemsFromServer(DeviceNetwork.onSuccessListener listener);

    interface onSuccessListener {
        void onSuccessGetDeviceItemsFromServer(List<DeviceItem> deviceItems);
    }
}
