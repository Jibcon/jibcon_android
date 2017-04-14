package com.example.admin.jipcon2.Cheatkey;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.jipcon2.R;

import java.util.ArrayList;

/**
 * Created by ChanJoo on 2017-04-14.
 */

public class TrickPassive extends android.support.v4.app.Fragment{
    ArrayList<String> txtArr1 = new ArrayList<String>();
    ArrayList<String> txtArr2 = new ArrayList<String>();

    ListView listView;
    public TrickPassive(){}

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        txtArr1.add("방의 온도 < 24 ");
        txtArr2.add("-난방 on, -전기장판 on");

        txtArr1.add("방의 온도가 > 30");
        txtArr2.add("-에어컨 on, -공기청정기 on");

        txtArr1.add("새벽인데 코딩중");
        txtArr2.add("소리벗고 바지질러");

        txtArr1.add("여자친구");
        txtArr2.add("off");



        View rootView2 = inflater.inflate(R.layout.cheatkey_passive, container, false);

        listView = (ListView)rootView2.findViewById(R.id.passiveListView);
        listView.setAdapter(new Myadapter());

        return rootView2;
    }
    public class Myadapter extends BaseAdapter {
        LayoutInflater inflater;

        public Myadapter(){
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return txtArr1.size();
        }

        @Override
        public Object getItem(int position) {
            return txtArr1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView = inflater.inflate(R.layout.cheatkey_passive_item,parent,false);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.passiveTextView1);
            textView.setText(txtArr1.get(position));

            TextView textView2 = (TextView) convertView.findViewById(R.id.passiveTextView2);
            textView2.setText(txtArr2.get(position));

            return convertView;
        }
    }
}
