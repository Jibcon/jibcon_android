package com.sm_arts.jibcon.data.models.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by woojinkim on 2017. 10. 8..
 */

public class NotiData implements Serializable{
    @SerializedName("time")
    @Expose
    public String time;

    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("message")
    @Expose
    public String message;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotiData(String time, String token, String message) {
        this.time = time;
        this.token = token;
        this.message = message;
    }
}
