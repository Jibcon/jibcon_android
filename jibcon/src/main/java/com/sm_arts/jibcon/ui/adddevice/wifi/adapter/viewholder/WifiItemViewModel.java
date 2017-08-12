package com.sm_arts.jibcon.ui.adddevice.wifi.adapter.viewholder;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.utils.converter.ObservableFieldConvertUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.disposables.DisposableContainer;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiItemViewModel extends ViewModel {
    private static final String TAG = "WifiItemViewModel";
    private final int WIFISRC_PLACEHOLDER = R.drawable.adddevice_wifirecycleritem_wifi_unknown_black_24dp;
    private final int WIFISRC_LOCK = R.drawable.adddevice_wifirecycleritem_wifi_lock_black_24dp;
    private final int WIFISRC_UNLOCK = R.drawable.adddevice_wifirecycleritem_wifi_unlock_black_24dp;

    public ObservableField<WifiItem> mItem = new ObservableField<>();

    public ObservableField<String> mSsid = new ObservableField<>();
    public ObservableField<Integer> mSecureSrc = new ObservableField<>();
    public ObservableField<String> mBandwidth = new ObservableField<>();

    private CompositeDisposable disposableContainer = new CompositeDisposable();

    public void setItem(WifiItem item) {
        this.mItem.set(item);
    }

    public WifiItemViewModel() {
        Observable<WifiItem> wifiItemObservable =
                ObservableFieldConvertUtils.toObservable(mItem);

        disposableContainer.add(
                wifiItemObservable.filter((item -> item == null))
                        .subscribe((isNull) -> this.setPlaceholder())
        );

        Observable<WifiItem> nonNullObservable = wifiItemObservable.filter((item -> item != null));

        disposableContainer.add(
                nonNullObservable.map(wifiItem -> wifiItem.ssid)
                        .subscribe((ssid) -> mSsid.set(ssid))
        );

        disposableContainer.add(
                nonNullObservable.map(wifiItem -> wifiItem.isSecured)
                                .map(isSecured -> isSecured ? WIFISRC_LOCK : WIFISRC_UNLOCK)
                        .subscribe((src) -> {
                            mSecureSrc.set(src);
                            Log.d(TAG, "WifiItemViewModel: mSecureSrc.set(src)" + src);
                        })
        );

        disposableContainer.add(
                nonNullObservable.map(wifiItem -> wifiItem.is5GHz)
                        .map(is5GHz -> is5GHz ? "5GHz" : "2.4GHz")
                        .subscribe((bandwidth) -> mBandwidth.set(bandwidth))
        );
    }

    private void setPlaceholder() {
        mSecureSrc.set(WIFISRC_PLACEHOLDER);
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        disposableContainer.dispose();
        super.onCleared();
    }
}
