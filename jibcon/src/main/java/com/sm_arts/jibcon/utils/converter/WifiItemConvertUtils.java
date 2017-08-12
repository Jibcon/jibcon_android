package com.sm_arts.jibcon.utils.converter;

import android.net.wifi.ScanResult;
import android.util.Log;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.utils.StringUtils;

import org.eclipse.paho.client.mqttv3.util.Strings;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiItemConvertUtils {
    private static final String TAG = "WifiItemConvertUtils";

    private static final String SECURE_METHOD_BLE = "BLE";
    private static final String SECURE_METHOD_WPA = "WPA";
    private static final String SECURE_METHOD_WPA2 = "WPA2";
    private static final String SECURE_METHOD_WPS = "WPS";
    // TODO: 8/12/17 EAP, ESS, PSK 정확히 뭔지 알아내야함
    private static final String SECURE_METHOD_EAP = "EAP";
    private static final String SECURE_METHOD_ESS = "ESS";

    // Capabilities Examples
    //            "[WPS]";
    //            "[BLE]";
    //            "[WPA2-PSK-CCMP+TKIP]";
    //            "[WPA2-PSK-CCMP]";
    //            "[WPA-PSK-CCMP+TKIP]";
    //            "[ESS]";


    public static @NonNull List<WifiItem> convertToWifiItem(@NonNull final List<ScanResult> scanResults) {
        List<WifiItem> wifiItems = new ArrayList<>();
        for (ScanResult scanResult :
                scanResults) {
            wifiItems.add(convertToWifiItem(scanResult));
        }

        return wifiItems;
    }

    private static @NonNull WifiItem convertToWifiItem(@NonNull final ScanResult scanResult) {
        boolean isSecured = true;

        WifiItem wifiItem = new WifiItem(
                scanResult.SSID,
                scanResult.BSSID,
                is5GHz(scanResult),
                isSecured(scanResult));
        return wifiItem;
    }

    private static boolean is5GHz(@NonNull ScanResult scanResult) {
        return is5GHz(scanResult.frequency);
    }

    private static boolean is5GHz(int freq) {
        return freq > 4900 && freq < 5900;
    }

    private static boolean isSecured(@NonNull ScanResult scanResult) {
        return isSecured(scanResult.capabilities);
    }

    private static boolean isSecured(@Nullable String capabilities) {
//        Log.d(TAG, "isSecured() called with: capabilities = " + capabilities);
        if (hasSecureMethod(capabilities, SECURE_METHOD_WPA)) {
            return true;
        }

        if (hasSecureMethod(capabilities, SECURE_METHOD_WPA2)) {
            return true;
        }

        if (hasSecureMethod(capabilities, SECURE_METHOD_WPS)) {
            return true;
        }

        if (hasSecureMethod(capabilities, SECURE_METHOD_EAP)) {
            return true;
        }

        if (hasSecureMethod(capabilities, SECURE_METHOD_ESS)) {
            return false;
        }

        if (hasSecureMethod(capabilities, SECURE_METHOD_BLE)) {
            return false;
        }

        Log.w(TAG, "isSecured: capabilities = " + capabilities + ", " +
                "it dosen't belong to any method.");
        return false;
    }

    private static boolean hasSecureMethod(@Nullable String capabilities, @NonNull String method) {
        // TODO: 8/12/17 METHDO는 무조건 처음에오는지 알아내야함
        return StringUtils.contains(capabilities, "[" + method + "-")
                || StringUtils.contains(capabilities, "[" + method + "]");
    }
}
