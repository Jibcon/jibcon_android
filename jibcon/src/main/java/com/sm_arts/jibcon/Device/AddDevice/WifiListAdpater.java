package com.sm_arts.jibcon.Device.AddDevice;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 2017-04-15.
 */

public class WifiListAdpater extends BaseAdapter {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    Context context;
    List<ScanResult> wifilist;

    public List<ScanResult> getWifilist() {
        return wifilist;
    }

    public void setWifilist(List<ScanResult> wifilist) {
        wifilist = sortByLevel(wifilist);
        this.wifilist = wifilist;
        notifyDataSetChanged();
    }

    private List<ScanResult> sortByLevel(List<ScanResult> wifilist) {
        Log.d(TAG, "sortByLevel: before "+wifilist.toString());
        Collections.sort(wifilist,new ScanResultComparator());
        Log.d(TAG, "sortByLevel: after "+wifilist.toString());
        return wifilist;
    }

    class ScanResultComparator implements Comparator<ScanResult> {
        @Override
        public int compare(ScanResult o1, ScanResult o2) {
            int first = o1.level;
            int second = o2.level;

            if (first > second) {
                return -1;
            } else if (first < second) {
                return 1;
            } else {
                return 0;
            }
        }
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

        TextView textView = (TextView)root.findViewById(R.id.tvWifi);
        ImageView imgView = (ImageView)root.findViewById(R.id.imgViewWifi);
        textView.setText(wifilist.get(position).SSID);

        switch ( getType(wifilist.get(position))) // todo get wifi pic
        {
            case "2.4Ghz/WPA2-AES":   BitmapDrawable drawable1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.airconditioner);
                Bitmap bitmap1 = drawable1.getBitmap();
                imgView.setImageBitmap(bitmap1);
                Log.d(TAG, "getView: "+wifilist.get(position).level);


                break;
        }

        return root;

    }

    private String getType(ScanResult scanresult) {
        return "2.4Ghz/WPA2-AES";
    } // todo implements
}
