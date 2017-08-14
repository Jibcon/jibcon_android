package com.sm_arts.jibcon.data.models.api.dto;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class Routine {
    public String id;
    public String title;
    public String sensor;
    public String sensorCnt;
    public String conditionMethod;
    public String value;
    public String unit;
    public String actuator;
    public String actuatorCnt;
    public String action;

    public Routine(String title, String sensor, String sensorCnt,
                   String conditionMethod, String value, String unit,
                   String actuator, String actuatorCnt, String action) {
        this.title = title;
        this.sensor = sensor;
        this.sensorCnt = sensorCnt;
        this.conditionMethod = conditionMethod;
        this.value = value;
        this.unit = unit;
        this.actuator = actuator;
        this.actuatorCnt = actuatorCnt;
        this.action = action;
    }
}
