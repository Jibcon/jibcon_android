package com.sm_arts.jibcon.ui.main.devicemenu.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.utils.helper.CustomItemClickListener;

/**
 * Created by jaeyoung on 7/18/17.
 */

public class DeviceMenuViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DeviceMenuViewHolder";

    private final ImageView mIvThreedot;
    private final ImageView mIvDeviceItem;
    private final TextView mTvDevicename;
    private final TextView mTvDevicecontent;
    private final TextView mTvPlacename;
    private final View mMotherView;

    public DeviceMenuViewHolder(View itemView,
                                CustomItemClickListener mDeviceItemIvClickedListener,
                                CustomItemClickListener mThreedotIvClickedListener) {
        super(itemView);
//        Log.d(TAG, "DeviceMenuViewHolder: ");

        mMotherView = itemView;
        mIvThreedot = (ImageView) itemView.findViewById(R.id.iv_devicemenuviewholder_option);
        mIvThreedot.setOnClickListener(
                v ->
                    mThreedotIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );

        mIvDeviceItem = (ImageView) itemView.findViewById(R.id.iv_devicemenuviewholder_icon);
        mMotherView.setOnClickListener(
                v->
                    mDeviceItemIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );
        mTvPlacename = (TextView) itemView.findViewById(R.id.tv_devicemenuviewholder_placename);
        mTvDevicename = (TextView) itemView.findViewById(R.id.tv_devicemenuviewholder_devicename);
        mTvDevicecontent = (TextView) itemView.findViewById(R.id.tv_devicemenuviewholder_status);
    }

    public void configureWith(DeviceItem deviceItem) {
        String deviceType = deviceItem.getDeviceType();

        if(!deviceItem.isDeviceOnOffState()) {
            mMotherView.setBackgroundResource(R.drawable.maindevicemenu_recycleritem_backgroundon);
        } else {
            mMotherView.setBackgroundResource(R.drawable.maindevicemenu_recycleritem_backgroundoff);
        }

        if(DeviceType.AIRCONDITIONER.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.maindevicemenu_recycleritem_monitor_vector24dp);
        } else if (DeviceType.LIGHTBULB.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.maindevicemenu_recycleritem_lightbulb_vector24dp);
        } else if (DeviceType.FAN.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.maindevicemenu_recycleritem_lightbulb_vector24dp);
        } else if (DeviceType.REFRIGERATOR.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.maindevicemenu_recycleritem_lightbulb_vector24dp);
        }

        mTvPlacename.setText(deviceItem.getRoomName());
        mTvDevicename.setText(deviceItem.getDeviceName());
        mTvDevicecontent.setText(deviceItem.getContent());
    }
}
