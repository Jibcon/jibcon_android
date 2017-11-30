package com.sm_arts.jibcon.ui.adddevice;

import android.support.v4.app.Fragment;

import java.util.HashMap;


/**
 * Created by admin on 2017-04-15.
 */

public interface AddDeviceListner {
    void setDeviceCom(String deviceCom);
    void setDeviceName(String deviceName);
    void setRoomName(String roomName);
    void setAeName(String aeName);
    void setCntName(String setCntName);
    void setData(HashMap<String,Object> data);

    void nextPage(Fragment fragment);

    void setDeviceType(String type);
}

