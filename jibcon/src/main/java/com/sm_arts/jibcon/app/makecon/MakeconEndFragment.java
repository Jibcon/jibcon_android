package com.sm_arts.jibcon.app.makecon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.main.MainActivity;
import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconEndFragment extends android.support.v4.app.Fragment {
    HouseInfoListener mHouseInfoListener;
    LinearLayout mLinearLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLinearLayout= (LinearLayout)inflater.inflate(R.layout.makecon_complete_fragment,container,false);

        Handler handler;
        handler=new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                //asynktask로 houseinfo 보낸 뒤 보내고 성공하면 MainActivity로, 보내는중엔 집콘 최적화화면만
                mHouseInfoListener.makeHouseInfo();
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        };

        handler.postDelayed(runnable,1500);

        return mLinearLayout;

    }
}