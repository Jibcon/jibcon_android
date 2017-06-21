package com.sm_arts.jibcon.model.deviceinfo;

/**
 * Created by admin on 2017-04-08.
 */

public class DeviceInfo {
//    DeviceAddr : 디바이스의 IP주소..?
//            -DeviceOnOffState : 디바이스의 On/Off state
//    -Devicetype : ex) 디바이스의 종류/ 선풍기 에어컨 전구 등등
//    res파일에 디바이스별 아이콘 저장 -> 디바이스 타입에 따라 맞는 아이콘 선택

    String deviceAddr;
    String deviceType;
    String deviceCom;//기기 회사

    public String getDeviceCom() {
        return deviceCom;
    }

    public void setDeviceCom(String deviceCom) {
        this.deviceCom = deviceCom;
    }

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
