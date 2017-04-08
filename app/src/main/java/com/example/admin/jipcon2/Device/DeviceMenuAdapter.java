package com.example.admin.jipcon2.Device;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.R;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceMenuAdapter extends BaseAdapter{

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
        return app.getDeviceItemArrayList().size();
    }

    @Override
    public Object getItem(int position) {

        return app.getDeviceItemArrayList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            Log.d("DeviceMenu","DeviceMenuNull");
        convertView= inflater.inflate(R.layout.device_item, parent, false);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.ImgViewDiviceItem);
        imageView.setImageBitmap(app.getDeviceItemArrayList().get(position).getImage());


        //Button button = (Button)convertView.findViewById(R.id.BtnDeviceItem);
        notifyDataSetChanged();


        return convertView;
    }
}
