package com.example.admin.jipcon2.Cheatkey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.jipcon2.R;

import java.util.ArrayList;

public class TrickActive extends android.support.v4.app.Fragment{
    GridView gridView=null;

    ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();
    ArrayList<String> textArr = new ArrayList<String>();

    public TrickActive(){}

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img0 = drawable1.getBitmap();
        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
        Bitmap img1 = drawable2.getBitmap();
        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img2 = drawable3.getBitmap();
        BitmapDrawable drawable4 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img3 = drawable4.getBitmap();
        BitmapDrawable drawable5 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
        Bitmap img4 = drawable5.getBitmap();
        BitmapDrawable drawable6 = (BitmapDrawable) getResources().getDrawable(R.drawable.option);
        Bitmap img5 = drawable6.getBitmap();
        BitmapDrawable drawable7 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
        Bitmap img6 = drawable7.getBitmap();
        BitmapDrawable drawable8 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img7 = drawable8.getBitmap();

        picArr.add(img0);
        picArr.add(img1);
        picArr.add(img2);
        picArr.add(img3);
        picArr.add(img4);
        picArr.add(img5);
        picArr.add(img6);
        picArr.add(img7);

        for(int i =0;i<8;i++)
        {
            textArr.add("num:"+Integer.toString(i));
        }

        View rootView = inflater.inflate(R.layout.cheatkey_active, container, false);

        gridView = (GridView)rootView.findViewById(R.id.maingridView);
        gridView.setAdapter(new Myadapter());


        return rootView;
    }

    //////////////////////

    public class Myadapter extends BaseAdapter {
        LayoutInflater inflater;

        public Myadapter(){
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return picArr.size();
        }

        @Override
        public Object getItem(int position) {
            return picArr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView = inflater.inflate(R.layout.cheatkey_active_gv,parent,false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.itemImageView);

            TextView textView = (TextView) convertView.findViewById(R.id.itemtextView);


            imageView.setImageBitmap(picArr.get(position));
            textView.setText(textArr.get(position));


            return convertView;
        }
    }

    ///////////////

}