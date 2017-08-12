package com.sm_arts.jibcon.ui.adddevice.wifi.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiItemViewModel extends ViewModel {
    public ObservableField<WifiItem> mItem = new ObservableField<>();

    public void setItem(WifiItem item) {
        this.mItem.set(item);
    }
}
