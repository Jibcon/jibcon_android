package com.sm_arts.jibcon.data.models.api.dto;

/**
 * Created by euijoonjung on 2018. 1. 28..
 */

public class WeatherDataDto {
    String lat;
    String lon;
    String city;

    public WeatherDataDto(String lat, String lon, String city) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;
    }
}
