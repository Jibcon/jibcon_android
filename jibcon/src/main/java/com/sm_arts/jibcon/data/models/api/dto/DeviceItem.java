package com.sm_arts.jibcon.data.models.api.dto;

import com.sm_arts.jibcon.utils.consts.MqttTopicUtils;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceItem {
    private String id;
    private String user;
    private String deviceCom;
    private String deviceName;
    private String deviceType;
    private String deviceWifiAddr;
    private boolean deviceOnOffState;
    private boolean subscribeOnOffState;
    private String roomName;
    private String aeName;
    private String cntName;
    private String content;

    public String getDeviceWifiAddr() {
        return deviceWifiAddr;
    }

    public boolean isSubscribeOnOffState() {
        return subscribeOnOffState;
    }

    public void setSubscribeOnOffState(boolean subscribeOnOffState) {
        this.subscribeOnOffState = subscribeOnOffState;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DeviceItem(int deviceType, String deviceName) {
        this.deviceType = new Integer(deviceType).toString();
        this.deviceName = deviceName;
    }

    public String getAeName() {
        return aeName;
    }

    public void setAeName(String aeName) {
        this.aeName = aeName;
    }

    public String getCntName() {
        return cntName;
    }

    public void setCntName(String cntName) {
        this.cntName = cntName;
    }

    public String getSubscriptionSur() {
        return MqttTopicUtils.makeSubscriptionSur(this);
    }

    public void setDeviceWifiAddr(String deviceWifiAddr) {
        this.deviceWifiAddr = deviceWifiAddr;
    }

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
