package com.sm_arts.jibcon.ui.adddevice.wifi.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.ui.adddevice.wifi.adapter.WifiAdapter;

import java.util.List;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class RecyclerViewBindingAdapter {
    private static final String TAG = "RecyclerViewBindingAdap";

    @BindingAdapter("items")
    public static void setItemsFromXml(RecyclerView recyclerView, List<WifiItem> items) {
        Log.d(TAG, "setItemsFromXml() called with: recyclerView = [" + recyclerView + "], items = [" + items + "]");
        WifiAdapter adapter = (WifiAdapter) recyclerView.getAdapter();

        adapter.setItems(items);
    }
}
