package com.sm_arts.jibcon.ui.main.devicemenu.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.utils.helper.CustomItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaeyoung on 7/18/17.
 */

public class DeviceMenuViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DeviceMenuViewHolder";

    @BindView(R.id.iv_devicemenuviewholder_subscribe)
    ImageView mIvSubscribe;
    @BindView(R.id.iv_devicemenuviewholder_option)
    ImageView mIvThreedot;
    @BindView(R.id.iv_devicemenuviewholder_icon)
    ImageView mIvDeviceItem;

    @BindView(R.id.tv_devicemenuviewholder_devicename)
    TextView mTvDevicename;
    @BindView(R.id.tv_devicemenuviewholder_status)
    TextView mTvDevicecontent;
    @BindView(R.id.tv_devicemenuviewholder_placename)
    TextView mTvPlacename;
    private final View mMotherView;

    public DeviceMenuViewHolder(View itemView,
                                CustomItemClickListener deviceItemIvClickedListener,
                                CustomItemClickListener subscribeIvClickedListener,
                                CustomItemClickListener threedotIvClickedListener) {
        super(itemView);
//        Log.d(TAG, "DeviceMenuViewHolder: ");

        ButterKnife.bind(this, itemView);
        mMotherView = itemView;
        mIvThreedot.setOnClickListener(
                v ->
                    threedotIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );

        mIvSubscribe.setOnClickListener(
                v ->
                        subscribeIvClickedListener.onItemClick(v,
                                getAdapterPosition())
        );

        mMotherView.setOnClickListener(
                v->
                    deviceItemIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );
    }

    public void configureWith(DeviceItem deviceItem) {
        String deviceType = deviceItem.getDeviceType();

        if(deviceItem.isDeviceOnOffState()) {
            mMotherView.setBackgroundResource(R.drawable.maindevicemenu_recycleritem_backgroundon);
        } else {
            mMotherView.setBackgroundResource(R.drawable.maindevicemenu_recycleritem_backgroundoff);
        }

        if(deviceItem.isSubscribeOnOffState()) {
            mIvSubscribe.setImageResource(R.drawable.maindevicemenu_recycleritem_notificationson24dp);
        } else {
            mIvSubscribe.setImageResource(R.drawable.maindevicemenu_recycleritem_notificationsnone24dp);
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
