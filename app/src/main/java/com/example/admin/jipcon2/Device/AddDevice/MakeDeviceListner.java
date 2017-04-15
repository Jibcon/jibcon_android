package com.example.admin.jipcon2.Device.AddDevice;

/**
 * Created by admin on 2017-04-15.
 */

public interface MakeDeviceListner {

    void setDeviceCom(String deviceCom);
    void setDeviceName(String deviceName);
    void setWifi(String wifi);
    void NextPage(int num);
}
