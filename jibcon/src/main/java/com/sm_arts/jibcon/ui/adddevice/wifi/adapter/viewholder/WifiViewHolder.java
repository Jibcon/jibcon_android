package com.sm_arts.jibcon.ui.adddevice.wifi.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sm_arts.jibcon.data.models.inapp.WifiItem;
import com.sm_arts.jibcon.databinding.DeviceWifilistadapterListviewItemBinding;

/**
 * Created by jaeyoung on 8/11/17.
 */

public class WifiViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "WifiViewHolder";

    private final WifiItemViewModel mViewModel;


    public WifiViewHolder(View view, DeviceWifilistadapterListviewItemBinding binding) {
        super(view);
        mViewModel = new WifiItemViewModel();
        binding.setWifiitemviewmodel(mViewModel);
    }

    public void configureWith(WifiItem item) {
        mViewModel.setItem(item);
    }
}
