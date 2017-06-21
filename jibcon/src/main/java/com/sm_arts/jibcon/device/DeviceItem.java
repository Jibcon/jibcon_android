package com.sm_arts.jibcon.device;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceItem {
    String deviceType;//디바이스 메뉴의 int 값. int 값으로 판별
    String deviceName;//디바이스 메뉴 아이템 이름 ex) 전등 알람 등등..
    String deviceWifiAddr;
    String id;
    String deviceCom;
    boolean deviceOnOffState;
    String user;

    public DeviceItem(int deviceType, String deviceName) {
        this.deviceType = new Integer(deviceType).toString();
        this.deviceName = deviceName;
    }
 

    public void setDeviceWifiAddr(String deviceWifiAddr) {
        this.deviceWifiAddr = deviceWifiAddr;
    }
//    strarr.add(0,"airconditioner");
//    strarr.add(1,"lightbulb");
//    strarr.add(2,"fan");
//    strarr.add(3,"refrigerator");
    //0 : 에어컨
    //1 : 전구
    //2 : 선풍기
    //3 : 냉장고


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isDeviceOnOffState() {
        return deviceOnOffState;
    }

    public void setDeviceOnOffState(boolean deviceOnOffState) {
        this.deviceOnOffState = deviceOnOffState;
    }

    public String getDeviceCom() {
        return deviceCom;
    }

    public void setDeviceCom(String deviceCom) {
        this.deviceCom = deviceCom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
