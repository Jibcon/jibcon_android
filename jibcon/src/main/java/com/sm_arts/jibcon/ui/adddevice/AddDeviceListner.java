package com.sm_arts.jibcon.ui.adddevice;

import android.net.wifi.ScanResult;

/**
 * Created by admin on 2017-04-15.
 */

public interface AddDeviceListner {
    void setDeviceCom(String deviceCom);
    void setDeviceName(String deviceName);
    void setRoomName(String roomName);
    void setAeName(String aeName);
    void setCntName(String setCntName);

    void nextPage(int num);
}

