package com.sm_arts.jibcon.ui.main.devicemenu.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
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
    @BindView(R.id.iv_devicemenuviewholder_onoff)
    ImageView mIvDeviceOnOff;

    @BindView(R.id.imgview_devicemenu_off)
    ImageView mButtonOff;
    @BindView(R.id.imgview_devicemenu_on)
    ImageView mButtonOn;


    private final View mMotherView;
    private CardView mCardView;

    public DeviceMenuViewHolder(View itemView,
                                CustomItemClickListener deviceItemIvClickedListener,
                                CustomItemClickListener subscribeIvClickedListener,
                                CustomItemClickListener threedotIvClickedListener,
                                CustomItemClickListener offButtonClickedListner,
                                CustomItemClickListener onButtonClickedListner) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        mMotherView = itemView;
        mCardView = (CardView) mMotherView.findViewById(R.id.cardview_devicemenuviewholder);
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
                v ->
                        deviceItemIvClickedListener.onItemClick(v,
                                getAdapterPosition())
        );

        mButtonOff.setOnClickListener(v -> {
            offButtonClickedListner.onItemClick(v, getAdapterPosition());
        });
        mButtonOn.setOnClickListener(v -> {
            onButtonClickedListner.onItemClick(v, getAdapterPosition());
        });

    }

    public void configureWith(DeviceItem deviceItem) {
        String deviceType = deviceItem.getDeviceType();

        if (deviceItem.isDeviceOnOffState()) {
            mIvDeviceOnOff.setImageResource(R.drawable.maindevicemenu_recycleritem_backgroundon);
        } else {
            mIvDeviceOnOff.setImageResource(R.drawable.maindevicemenu_recycleritem_backgroundoff);
        }

        if (deviceItem.isSubscribeOnOffState()) {
            mIvSubscribe.setImageResource(R.drawable.maindevicemenu_recycleritem_notificationson24dp);
        } else {
            mIvSubscribe.setImageResource(R.drawable.maindevicemenu_recycleritem_notificationsnone24dp);
        }

        if (DeviceType.AIRCONDITIONER.equals(deviceType)) {
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
