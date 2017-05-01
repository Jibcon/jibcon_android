package com.sm_arts.jibcon.MakeCon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon3 extends android.support.v4.app.Fragment {
    String houselocation;
    Button before;
    Button next;

    HouseInfoListener houseInfoListener;
    LinearLayout linearLayout;

    private void initLayout()
    {
        before= (Button)linearLayout.findViewById(R.id.Btn_MakeCon3_0);
        next =  (Button)linearLayout.findViewById(R.id.Btn_makeCon3_1);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseInfoListener = (HouseInfoListener)context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = (LinearLayout) inflater.inflate(R.layout.makecon3,container,false);
        initLayout();

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseInfoListener.getFragmentNum(-1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseInfoListener.getFragmentNum(1);
            }
        });


        return linearLayout;
    }
}
