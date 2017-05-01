package com.sm_arts.jibcon.Device.service;

import com.sm_arts.jibcon.Device.DeviceItem;

import java.util.List;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public interface DeviceService {
    void getDeviceItems(onSuccessListener listener);
    List<DeviceItem> getDeviceItems();
    void notifyDeviceItemsChanged();
    void prepareDeviceItems();
    void reloadDeviceItems(DeviceService.onSuccessListener listener);

    interface onSuccessListener {
        void onSuccessGetDeviceItems(List<DeviceItem> deviceItems);
    }
}
