package com.example.admin.jipcon2.Device.AddDevice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.jipcon2.R;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-15.
 */

public class WifiListAdpater extends BaseAdapter {
    Context context;
    ArrayList<String> wifilist;

    public ArrayList<String> getWifilist() {
        return wifilist;
    }

    public void setWifilist(ArrayList<String> wifilist) {
        this.wifilist = wifilist;
        notifyDataSetChanged();
    }

    public WifiListAdpater(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return wifilist.size();
    }

    @Override
    public Object getItem(int position) {
        return wifilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = View.inflate(parent.getContext(), R.layout.add_device2_wifi_item,null);

        TextView textView = (TextView)root.findViewById(R.id.Txt_adddevice2_0);
        textView.setText(wifilist.get(position));


        return root;

    }
}
