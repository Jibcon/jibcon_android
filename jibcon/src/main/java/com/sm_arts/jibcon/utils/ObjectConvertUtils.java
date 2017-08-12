package com.sm_arts.jibcon.utils;

import android.net.wifi.ScanResult;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class ObjectConvertUtils {
    public static List<WifiItem> convertToWifiItem(List<ScanResult> scanResults) {
        List<WifiItem> wifiItems = new ArrayList<>();
        for (ScanResult scanResult :
                scanResults) {
            wifiItems.add(convertToWifiItem(scanResult));
        }

        return wifiItems;
    }

    private static WifiItem convertToWifiItem(ScanResult scanResult) {
        WifiItem wifiItem = new WifiItem(scanResult.SSID);
        return wifiItem;
    }
}
