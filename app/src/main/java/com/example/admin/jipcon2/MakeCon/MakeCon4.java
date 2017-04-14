package com.example.admin.jipcon2.MakeCon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.admin.jipcon2.MainActivity;
import com.example.admin.jipcon2.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon4 extends android.support.v4.app.Fragment {
     Button before;
    HouseInfoListener houseInfoListener;

    LinearLayout linearLayout;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseInfoListener = (HouseInfoListener)context;
    }
    private void initLayout()
    {
        before=(Button)linearLayout.findViewById(R.id.Btn_makeCon4_0);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         linearLayout= (LinearLayout)inflater.inflate(R.layout.makecon4,container,false);

        initLayout();
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseInfoListener.getFragmentNum(-1);
            }
        });

        Handler handler;
        handler=new Handler();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                //asynktask로 houseinfo 보낸 뒤 보내고 성공하면 MainActivity로, 보내는중엔 집콘 최적화화면만
                houseInfoListener.makeHouseInfo();
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        };

        handler.postDelayed(runnable,1500);
        return linearLayout;

    }
}
