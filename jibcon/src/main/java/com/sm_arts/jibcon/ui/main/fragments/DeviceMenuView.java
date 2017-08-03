package com.sm_arts.jibcon.ui.main.fragments;

import com.sm_arts.jibcon.data.models.DeviceItem;
import com.sm_arts.jibcon.ui.main.adapters.DeviceMenuAdapter;

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

    DeviceMenuAdapter getAdapter();

    void showContent(int position, String con);
}
