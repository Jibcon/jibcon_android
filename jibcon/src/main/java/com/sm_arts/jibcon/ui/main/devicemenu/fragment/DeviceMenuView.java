package com.sm_arts.jibcon.ui.main.devicemenu.fragment;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.ui.main.devicemenu.adapter.DeviceMenuAdapter;
import com.sm_arts.jibcon.utils.helper.WeatherData;

import java.util.List;

/**
 * Created by jaeyoung on 7/21/17.
 */

public interface DeviceMenuView {
    void showDeviceDialog(int position);

    void gotoFloatingButtonDeviceActivity();

    void setSwiperefreshingOff();

    void refreshDeviceItems(List<DeviceItem> deviceItems);

    void updateDevicesOnOffState();

    DeviceMenuAdapter getAdapter();

    void showContent(int position, String con);

    void setWeatherInfo(WeatherData weatherData);
}
