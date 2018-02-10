package com.sm_arts.jibcon.ui.main.datacontrol;

import com.google.gson.internal.LinkedTreeMap;

/**
 * Created by euijoonjung on 2018. 1. 31..
 */

public interface DataControllView {
    public void onDownloadReady();
    public void onDownloadFinished();
    public void drawingWeatherChart(LinkedTreeMap<String, Object> map);

    void drawingHumidityChart(LinkedTreeMap<String, Object> body);
}
