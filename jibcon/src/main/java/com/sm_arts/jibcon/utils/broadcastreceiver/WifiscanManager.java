package com.sm_arts.jibcon.utils.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.utils.converter.WifiItemConvertUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiscanManager extends BroadcastReceiver {
    private static final String TAG = "WifiscanManager";
    private static final String RECEIVE_ACTION = WifiManager.SCAN_RESULTS_AVAILABLE_ACTION;
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

        IntentFilter intentFilter = new IntentFilter(RECEIVE_ACTION);
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
//        Log.d(TAG, "onReceive: ");
        List<ScanResult> scanResults = mWifiManager.getScanResults();
        List<WifiItem> wifiItems = WifiItemConvertUtils.convertToWifiItem(scanResults);
        if (TextUtils.equals(intent.getAction(), RECEIVE_ACTION)) {
            mNotifier.onNext(wifiItems);
        } else {
            Log.d(TAG, "onReceive() called with: action = [" + intent.getAction() + "]," +
                    " items = [" + wifiItems + "]");
        }
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
