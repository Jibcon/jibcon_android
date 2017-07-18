package com.sm_arts.jibcon.ui.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.network.MobiusService;
import com.sm_arts.jibcon.ui.main.adapters.viewholder.DeviceMenuViewHolder;
import com.sm_arts.jibcon.utils.helper.CustomItemClickListener;
import com.sm_arts.jibcon.utils.network.RetrofitUtils;
import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceMenuAdapter extends RecyclerView.Adapter<DeviceMenuViewHolder> {
    private static final String TAG = "DeviceMenuAdapter";

    private List<DeviceItem> mDeviceItems;
    private CustomItemClickListener mListener;

    public DeviceMenuAdapter(List<DeviceItem> deviceItems, CustomItemClickListener listener) {
        this.mDeviceItems = deviceItems;
        this.mListener = listener;
    }

    @Override
    public DeviceMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View deviceMenuView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_devicemenuadapter_cardview,
                        parent, false);

        final DeviceMenuViewHolder deviceMenuViewHolder = new DeviceMenuViewHolder(deviceMenuView);

        deviceMenuView.setOnClickListener(v ->
                mListener.onItemClick(v, deviceMenuViewHolder.getAdapterPosition())
        );

        return deviceMenuViewHolder;
    }

    @Override
    public void onBindViewHolder(DeviceMenuViewHolder holder, int position) {
        DeviceItem deviceItem = mDeviceItems.get(position);
        holder.configureWith(deviceItem);
    }

    @Override
    public int getItemCount() {
        return mDeviceItems.size();
    }
}