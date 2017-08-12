package com.sm_arts.jibcon.ui.adddevice.wifi.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.utils.broadcastreceiver.WifiscanManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiViewModel extends ViewModel{
    private static final String TAG = "WifiViewModel";

    public ObservableField<List<WifiItem>> mItems = new ObservableField<>(new ArrayList<>());
    public ObservableField<Boolean> searchProgress = new ObservableField<>(false);
    public ObservableField<WifiItem> selectedItem = new ObservableField<>();

    private WifiscanManager mWifiManager;

    WifiViewModel() {
    }

    public void onWifireceiverInitialized(WifiscanManager wifiscanManager) {
        Log.d(TAG, "onWifireceiverInitialized: ");

        mWifiManager = wifiscanManager;
        mWifiManager.asObserverble().subscribe(
                (items) -> {
                    Log.d(TAG, "onNext: items.size="+items.size());
                    searchProgress.set(false);
                    mItems.set(items);
                }
        );
        mWifiManager.asObserverble()
                .map(List::size)
                .filter((sz) -> sz < 0)
                .subscribe(
                        (len) -> {
                            Log.d(TAG, "onWifireceiverInitialized: Do rescan, len=" + len);
                            this.startScan();
                        }
                );

        startScan();
    }

    void startScan() {
        Log.d(TAG, "startScan: ");
        searchProgress.set(true);
        mWifiManager.startScan();
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        super.onCleared();
        mWifiManager.destroy();
    }
}
