package com.sm_arts.jibcon.data.models.mobius;

/**
 * Created by jaeyoung on 8/4/17.
 */

public class MqttSurCon {
    private String sur;
    private String con;

    public MqttSurCon(String sur, String con) {
        this.sur = sur;
        this.con = con;
    }

    public String getSur() {
        return sur;
    }

    public void setSur(String sur) {
        this.sur = sur;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    @Override
    public String toString() {
        return "MqttSurCon{" +
                "sur='" + sur + '\'' +
                ", con='" + con + '\'' +
                '}';
    }
}
