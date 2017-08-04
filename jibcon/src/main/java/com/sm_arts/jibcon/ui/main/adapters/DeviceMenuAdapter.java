package com.sm_arts.jibcon.ui.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.ui.main.adapters.viewholder.DeviceMenuViewHolder;
import com.sm_arts.jibcon.utils.helper.CustomItemClickListener;
import com.sm_arts.jibcon.R;

import java.util.List;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceMenuAdapter extends RecyclerView.Adapter<DeviceMenuViewHolder> {
    private static final String TAG = "DeviceMenuAdapter";
    private List<DeviceItem> mDeviceItems;

    private CustomItemClickListener mDeviceItemIvClickedListener;
    private CustomItemClickListener mThreedotIvClickedListener;

    public DeviceMenuAdapter(List<DeviceItem> deviceItems,
                             CustomItemClickListener deviceItemIvClickedListener,
                             CustomItemClickListener threedotIvClicked) {
//        Log.d(TAG, "DeviceMenuAdapter: ");
        this.mDeviceItems = deviceItems;
        this.mDeviceItemIvClickedListener = deviceItemIvClickedListener;
        this.mThreedotIvClickedListener = threedotIvClicked;
    }

    public void setDeviceItems(List<DeviceItem> mDeviceItems) {
//        Log.d(TAG, "setDeviceItems: ");
        this.mDeviceItems = mDeviceItems;
        notifyDataSetChanged();
    }

    @Override
    public DeviceMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder: ");
        View deviceMenuView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_devicemenuadapter_cardview,
                        parent, false);

        final DeviceMenuViewHolder deviceMenuViewHolder =
                new DeviceMenuViewHolder(deviceMenuView,
                        mDeviceItemIvClickedListener,
                        mThreedotIvClickedListener);

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

    public DeviceItem getItemWithPosition(int position) {
        if(getItemCount() > position) {
            return mDeviceItems.get(position);
        } else {
            return null;
        }
    }

    public int findDeviceItemPositionWithSur(String sur) {
        Log.d(TAG, "findDeviceItemPositionWithSur() called with: topic = [" + sur + "]");
        for (int idx = 0; idx < mDeviceItems.size(); idx++) {
            String itemSur = mDeviceItems.get(idx).getSubscriptionSur();
            if (itemSur != null) {
                if (TextUtils.equals(itemSur, sur)) {
                    Log.d(TAG, "findDeviceItemPositionWithSur: found idx=" + idx);
                    return idx;
                }
            }
        }

        return -1;
    }

    public void showContent(int position, String con) {
        Log.d(TAG, "showContent() called with: position = [" + position + "], con = [" + con + "]");
        mDeviceItems.get(position).setContent(con);
        notifyItemChanged(position);
    }
}