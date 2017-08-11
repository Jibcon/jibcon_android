package com.sm_arts.jibcon.utils.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiscanBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiBroadcastReceiver";
    private static WifiscanBroadcastReceiver sInstance;
    private final WifiManager mWifiManager;
    private final Context mContext;

    private WifiscanBroadcastReceiver(Context context) {
        WifiManager wifiManager;
        mContext = context;
        wifiManager = (WifiManager) mContext.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        mWifiManager = wifiManager;

        IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
//        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
//        intentFilter.addAction(WifiManager.ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE);
        mContext.registerReceiver(this,
                intentFilter);
    }

    public static WifiscanBroadcastReceiver getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init(Context) first");
        }

        return sInstance;
    }

    public void startScans() {
        mWifiManager.startScan();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: context = [" + context.toString() + "]," +
                " intent = [" + intent.toString() + "]");

        List<ScanResult> scanResults = mWifiManager.getScanResults();
        mWifiManager.startScan();
        if (scanResults.size() > 0) {
            Log.d(TAG, "initWifiManager: scanresult=" + scanResults.toString());
        }
    }

    public static void init(Context context) {
        sInstance = new WifiscanBroadcastReceiver(context);
    }

    public void destroy() {
        Log.d(TAG, "destroy: ");
        mContext.unregisterReceiver(this);
        sInstance = null;
    }
}
