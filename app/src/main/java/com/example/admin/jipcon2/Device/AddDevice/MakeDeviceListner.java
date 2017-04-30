package com.example.admin.jipcon2.Device.AddDevice;

import android.net.wifi.ScanResult;

/**
 * Created by admin on 2017-04-15.
 */

public interface MakeDeviceListner {

    void setDeviceCom(String deviceCom);
    void setDeviceName(String deviceName);
    void setWifi(ScanResult wifi);
    void NextPage(int num);
}
