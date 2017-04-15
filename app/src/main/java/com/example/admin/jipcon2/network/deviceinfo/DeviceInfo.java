package com.example.admin.jipcon2.network.deviceinfo;

/**
 * Created by admin on 2017-04-08.
 */

public class DeviceInfo {
//    DeviceAddr : 디바이스의 IP주소..?
//            -DeviceOnOffState : 디바이스의 On/Off state
//    -Devicetype : ex) 디바이스의 종류/ 선풍기 에어컨 전구 등등
//    res파일에 디바이스별 아이콘 저장 -> 디바이스 타입에 따라 맞는 아이콘 선택

    String DeviceAddr;
    String DeviceType;
    String DeviceCom;//기기 회사

    public String getDeviceCom() {
        return DeviceCom;
    }

    public void setDeviceCom(String deviceCom) {
        DeviceCom = deviceCom;
    }

    public String getDeviceAddr() {
        return DeviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        DeviceAddr = deviceAddr;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }
}
