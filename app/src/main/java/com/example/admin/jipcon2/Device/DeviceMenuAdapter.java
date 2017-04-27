package com.example.admin.jipcon2.Device;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
        //imageView.setImageBitmap(app.getDeviceItemArrayList().get(position).getImage());
        switch ( deviceItems.get(position).getDeviceType())
        {
            //0 : 에어컨
            //1 : 전구
            //2 : 선풍기
            //3 : 냉장고

            case "에어컨":   BitmapDrawable drawable1 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.airconditioner);
            Bitmap bitmap1 = drawable1.getBitmap();
              imageView.setImageBitmap(bitmap1);
                break;
            case "전구":
                BitmapDrawable drawable2 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.lightbulb);
                Bitmap bitmap2 = drawable2.getBitmap();
                imageView.setImageBitmap(bitmap2);
                break;
            case "선풍기":
                BitmapDrawable drawable3 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.fan);
                Bitmap bitmap3 = drawable3.getBitmap();
                imageView.setImageBitmap(bitmap3);
                break;
            case "냉장고":
                BitmapDrawable drawable4 = (BitmapDrawable) context.getResources().getDrawable(R.drawable.refrigerator);
                Bitmap bitmap4 = drawable4.getBitmap();
                imageView.setImageBitmap(bitmap4);
                break;
        }

        //Button button = (Button)convertView.findViewById(R.id.BtnDeviceItem);
        //notifyDataSetChanged();


        return convertView;
    }
}
//
//public class DeviceMenuAdapter extends BaseAdapter
//{
//
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        return null;
//    }
//}