package com.sm_arts.jibcon.ui.adddevice.wifi.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.databinding.AdddeviceWifiItemBinding;
import com.sm_arts.jibcon.ui.adddevice.wifi.adapter.viewholder.WifiViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiAdapter extends RecyclerView.Adapter<WifiViewHolder> {
    private static final String TAG = "WifiAdapter";
    private List<WifiItem> mItems;

    public WifiAdapter() {
        this.mItems = new ArrayList<>();
    }

    @Override
    public WifiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdddeviceWifiItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adddevice_wifi_item,
                parent,
                false);
        View v = binding.getRoot();


        final WifiViewHolder viewholder =
                new WifiViewHolder(v, binding);

        return viewholder;
    }

    public void setItems(List<WifiItem> items) {
//        Log.d(TAG, "setItems() called with: items = [" + items + "]");
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(WifiViewHolder holder, int position) {
        WifiItem deviceItem = mItems.get(position);
        holder.configureWith(deviceItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

