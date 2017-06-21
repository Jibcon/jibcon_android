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

    LayoutInflater inflater;
    GlobalApplication app;
    Context context;
    ArrayList<DeviceItem> deviceItems;

    public ArrayList<DeviceItem> getDeviceItems() {
        return deviceItems;
    }

    public void setDeviceItems(ArrayList<DeviceItem> deviceItems) {
        this.deviceItems = deviceItems;
        notifyDataSetChanged();
    }

    public DeviceMenuAdapter(Context mContext) {
        this.context=mContext;
        inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        app=(GlobalApplication)mContext.getApplicationContext();
    }

    @Override
    public int getCount() {
        return deviceItems.size();
    }

    @Override
    public Object getItem(int position) {

        return deviceItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if(convertView==null)
            Log.d("DeviceMenu","DeviceMenuNull");
        convertView= inflater.inflate(R.layout.device_item, parent, false);
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
        //imageView.setImageBitmap(app.getDeviceItemArrayList().get(position).getImage());
        switch ( deviceItems.get(position).getDeviceType())
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