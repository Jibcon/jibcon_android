package com.sm_arts.jibcon.ui.main.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.network.MobiusService;
import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.utils.network.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 7/18/17.
 */

public class DeviceMenuViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DeviceMenuViewHolder";

    private final ImageView mThreedotIv;
    private final ImageView mDeviceItemIv;

    public DeviceMenuViewHolder(View itemView) {
        super(itemView);

        mThreedotIv = (ImageView) itemView.findViewById(R.id.ImgView_deviceItem_threedot);
        mThreedotIv.setOnClickListener(
                v -> {
                    DeviceDialog deviceDialog = new DeviceDialog(itemView.getContext());
                    deviceDialog.show();
                }
        );

        mDeviceItemIv = (ImageView) itemView.findViewById(R.id.deviceItemIv);
        mDeviceItemIv.setOnClickListener(
                v-> {
                    Log.d(TAG, "onClick: ");
                    MobiusService service = (MobiusService) RetrofitUtils.getInstance().getService(MobiusService.class);
                    Call<Object> call = service.turnOnLed(
                            "application/json",
                            "1",
                            "/0.1",
                            "application/vnd.onem2m-res+json; ty=4",
                            new MobiusService.ApiCinC(3)
                    );

                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG, "onResponse: ");
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG, "onFailure: ");
                        }
                    });
                });
    }

    public void configureWith(DeviceItem deviceItem) {
        String deviceType = deviceItem.getDeviceType();

        if(DeviceType.AIRCONDITIONER.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.airconditioner);
        } else if (DeviceType.LIGHTBULB.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.lightbulb);
        } else if (DeviceType.FAN.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.fan);
        } else if (DeviceType.REFRIGERATOR.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.refrigerator);
        }
    }
}
