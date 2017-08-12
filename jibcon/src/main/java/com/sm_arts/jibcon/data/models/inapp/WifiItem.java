package com.sm_arts.jibcon.data.models.inapp;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiItem {
    public boolean is5GHz;
    public boolean isSecured;
    public String ssid;
    public String bssid;

    public WifiItem(String ssid, String bssid, boolean is5GHz, boolean isSecured) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.is5GHz = is5GHz;
        this.isSecured = isSecured;
    }
}
