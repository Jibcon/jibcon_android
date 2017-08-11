package com.sm_arts.jibcon.data.models.inapp;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiItem {
    private String wifiname;
    private int wifitype;

    public WifiItem(String wifiname) {
        this.wifiname = wifiname;
    }

    public String getWifiname() {
        return wifiname;
    }

    public void setWifiname(String wifiname) {
        this.wifiname = wifiname;
    }

    public int getWifitype() {
        return wifitype;
    }

    public void setWifitype(int wifitype) {
        this.wifitype = wifitype;
    }
}
