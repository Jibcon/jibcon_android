package com.sm_arts.jibcon.device.adddevice;

import android.net.wifi.ScanResult;

/**
 * Created by admin on 2017-04-15.
 */

public interface AddDeviceListner {
    void setDeviceCom(String deviceCom);
    void setDeviceName(String deviceName);
    void setWifi(ScanResult wifi);
    void nextPage(int num);

}
