package com.sm_arts.jibcon.device.service.network;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;

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
