package com.sm_arts.jibcon.ui.main.fragments;

import com.sm_arts.jibcon.device.DeviceItem;

import java.util.List;

/**
 * Created by jaeyoung on 7/21/17.
 */

interface DeviceMenuView {
    void showDeviceDialog();

    void gotoFloatingButtonDeviceActivity();

    void setSwiperefreshingOff();

    void refreshDeviceItems(List<DeviceItem> deviceItems);

    void updateDevicesOnOffState();
}
