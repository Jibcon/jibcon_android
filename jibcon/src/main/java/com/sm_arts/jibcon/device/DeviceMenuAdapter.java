package com.sm_arts.jibcon.device;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sm_arts.jibcon.network.MobiusService;
import com.sm_arts.jibcon.utils.network.RetrofitUtils;
import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceMenuAdapter extends BaseAdapter {
    private static final String TAG = "DeviceMenuAdapter";

    private final String AE = "";

    LayoutInflater mInflater;
    GlobalApplication mApp;
    Context mContext;
    ArrayList<DeviceItem> mDeviceItems;

    private boolean mLedFlag;
    private static int sReqid = 0;

    public ArrayList<DeviceItem> getDeviceItems() {
        return mDeviceItems;
    }

    public void setDeviceItems(ArrayList<DeviceItem> deviceItems) {
        this.mDeviceItems = deviceItems;
        notifyDataSetChanged();
    }

    public DeviceMenuAdapter(Context context) {
        this.mContext = context;
        mLedFlag = false;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mApp = (GlobalApplication) mContext.getApplicationContext();
    }

    @Override
    public int getCount() {
        return mDeviceItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDeviceItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if(convertView == null) {
            Log.d("DeviceMenu", "DeviceMenuNull");
        }
        convertView = mInflater.inflate(R.layout.device_devicemenuadapter_cardview, parent, false);
        ImageView threedot = (ImageView)convertView.findViewById(R.id.ImgView_deviceItem_threedot);
        threedot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceDialog deviceDialog = new DeviceDialog(parent.getContext());
                deviceDialog.show();
             }
        });
        //threedot.bringToFront();

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.ImgViewDiviceItem);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                MobiusService service = (MobiusService) RetrofitUtils.getInstance().getService(MobiusService.class);
                int con = (mLedFlag) ? 2 : 1;

                Call<Object> call = service.turnOnLed(
                        "application/json",
                        Integer.toString(sReqid),
                        "/" + AE,
                        "application/vnd.onem2m-res+json; ty=4",
                        new MobiusService.ApiCinC(con)
                        );

                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: code=" + response.code());
                            Log.d(TAG, "onResponse: message=" + response.message());
                            int color = mLedFlag ? Color.WHITE : Color.RED;
                            imageView.setBackgroundColor(color);
                            mLedFlag = !mLedFlag;
                            sReqid++;
                        } else {
                            Log.d(TAG, "onResponse: code=" + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
            }
        });
        //imageView.setImageBitmap(mApp.getDeviceItemArrayList().get(position).getImage());
        switch (mDeviceItems.get(position).getDeviceType()) {
            //0 : 에어컨
            //1 : 전구
            //2 : 선풍기
            //3 : 냉장고
            case "0":
                imageView.setImageResource(R.drawable.airconditioner);
                break;
            case "1":
                imageView.setImageResource(R.drawable.lightbulb);
                break;
            case "2":
                imageView.setImageResource(R.drawable.fan);
                break;
            case "3":
                imageView.setImageResource(R.drawable.refrigerator);
                break;
        }

        return convertView;
    }
}