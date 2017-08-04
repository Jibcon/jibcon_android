package com.sm_arts.jibcon.ui.main.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.GlobalApplication;
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

    public DeviceMenuViewHolder(View itemView,
                                CustomItemClickListener mDeviceItemIvClickedListener,
                                CustomItemClickListener mThreedotIvClickedListener) {
        super(itemView);
//        Log.d(TAG, "DeviceMenuViewHolder: ");

        mIvThreedot = (ImageView) itemView.findViewById(R.id.ImgView_deviceItem_threedot);
        mIvThreedot.setOnClickListener(
                v ->
                    mThreedotIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );

        mIvDeviceItem = (ImageView) itemView.findViewById(R.id.deviceItemIv);
        mIvDeviceItem.setOnClickListener(
                v->
                    mDeviceItemIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );
        mTvDevicename = (TextView) itemView.findViewById(R.id.tv_devicename);
        mTvDevicecontent = (TextView) itemView.findViewById(R.id.tv_devicecontent);
    }

    public void configureWith(DeviceItem deviceItem) {
        String deviceType = deviceItem.getDeviceType();

        if(!deviceItem.isDeviceOnOffState()) {
            mIvDeviceItem.setBackgroundColor(GlobalApplication.getGlobalApplicationContext().getResources().getColor(R.color.black_opaque));
        } else {
            mIvDeviceItem.setBackgroundColor(GlobalApplication.getGlobalApplicationContext().getResources().getColor(R.color.white));
        }

        if(DeviceType.AIRCONDITIONER.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.airconditioner);
        } else if (DeviceType.LIGHTBULB.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.lightbulb);
        } else if (DeviceType.FAN.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.fan);
        } else if (DeviceType.REFRIGERATOR.equals(deviceType)) {
            mIvDeviceItem.setImageResource(R.drawable.refrigerator);
        }

        mTvDevicename.setText(deviceItem.getDeviceName());
        mTvDevicecontent.setText(deviceItem.getContent());
    }
}
