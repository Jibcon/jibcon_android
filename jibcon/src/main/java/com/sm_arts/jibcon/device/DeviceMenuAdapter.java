package com.sm_arts.jibcon.device;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceMenuAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    GlobalApplication mApp;
    Context mContext;
    ArrayList<DeviceItem> mDeviceItems;

    public ArrayList<DeviceItem> getDeviceItems() {
        return mDeviceItems;
    }

    public void setDeviceItems(ArrayList<DeviceItem> deviceItems) {
        this.mDeviceItems = deviceItems;
        notifyDataSetChanged();
    }

    public DeviceMenuAdapter(Context context) {
        this.mContext=context;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mApp=(GlobalApplication)mContext.getApplicationContext();
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
        if(convertView==null)
            Log.d("DeviceMenu","DeviceMenuNull");
        convertView= mInflater.inflate(R.layout.device_deviceitem_cardview, parent, false);
        ImageView threedot = (ImageView)convertView.findViewById(R.id.ImgView_deviceItem_threedot);
        threedot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceDialog deviceDialog = new DeviceDialog(parent.getContext());
                deviceDialog.show();
             }
        });
        //threedot.bringToFront();

        ImageView imageView = (ImageView)convertView.findViewById(R.id.ImgViewDiviceItem);
        //imageView.setImageBitmap(mApp.getDeviceItemArrayList().get(position).getImage());
        switch ( mDeviceItems.get(position).getDeviceType())
        {
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