package com.sm_arts.jibcon.utils.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.utils.ObjectConvertUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiscanManager extends BroadcastReceiver {
    private static final String TAG = "WifiscanManager";
    private static WifiscanManager sInstance;
    private final WifiManager mWifiManager;
    private ReplaySubject<List<WifiItem>> mNotifier = ReplaySubject.create();
    private final Context mContext;

    private WifiscanManager(Context context) {
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

    public static WifiscanManager getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init(Context) first");
        }

        return sInstance;
    }

    public void startScan() {
        mWifiManager.startScan();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        //// TODO: 8/11/17 IMPLEMENT INTENT VALIDATION
        List<ScanResult> scanResults = mWifiManager.getScanResults();
        mWifiManager.startScan();
        List<WifiItem> wifiItems = ObjectConvertUtils.convertToWifiItem(scanResults);
        mNotifier.onNext(wifiItems);
    }

    public static void init(Context context) {
        sInstance = new WifiscanManager(context);
    }

    public void destroy() {
        Log.d(TAG, "destroy: ");
        mContext.unregisterReceiver(this);
        sInstance = null;
    }

    public Observable<List<WifiItem>> asObserverble() {
        return mNotifier;
    }
}
