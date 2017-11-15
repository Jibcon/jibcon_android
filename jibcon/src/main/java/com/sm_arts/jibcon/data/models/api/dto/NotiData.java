package com.sm_arts.jibcon.data.models.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by woojinkim on 2017. 10. 8..
 */

public class NotiData implements Serializable{

    @SerializedName("hour")
    @Expose
    public String hour;

    @SerializedName("minute")
    @Expose
    public String minute;

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("triggertype")
    @Expose
    public String triggertype;

    @SerializedName("actiontype")
    @Expose
    public String actiontype;

    @SerializedName("lat")
    @Expose
    public String lat;

    @SerializedName("lon")
    @Expose
    public String lon;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTriggertype() {
        return triggertype;
    }

    public void setTriggertype(String triggertype) {
        this.triggertype = triggertype;
    }

    public String getActiontype() {
        return actiontype;
    }

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public NotiData(String hour, String minute, String token, String triggertype,
                    String actiontype, String lat, String lon) {
        this.hour = hour;
        this.minute = minute;

        this.token = token;

        this.triggertype = triggertype;
        this.actiontype = actiontype;

        this.lat = lat;
        this.lon = lon;
    }
}
