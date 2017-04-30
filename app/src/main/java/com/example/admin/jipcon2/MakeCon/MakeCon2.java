package com.example.admin.jipcon2.MakeCon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.jipcon2.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon2 extends android.support.v4.app.Fragment {

    RelativeLayout relativeLayout;
    Button apart;
    Button villa;
    Button container;
    Button guard;
    Button office;
    Button next;
    Button before;

    TextView textView;
    HouseInfoListener houseInfoListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseInfoListener = (HouseInfoListener)context;
    }

    void initLayout()
    {
        // 리스트로 바꾸기!(20170430)

        /*
        apart = (Button)relativeLayout.findViewById(R.id.Btn_makeCon2_1);
        villa=(Button)relativeLayout.findViewById(R.id.Btn_makeCon2_2);
        container=(Button)relativeLayout.findViewById(R.id.Btn_makeCon2_3);
        guard=(Button)relativeLayout.findViewById(R.id.Btn_makeCon2_4);
        office=(Button)relativeLayout.findViewById(R.id.Btn_makeCon2_5);
        next =(Button)relativeLayout.findViewById(R.id.Btn_makeCon2_5);
        before=(Button)relativeLayout.findViewById(R.id.Btn_makeCon2_0);
        */

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        relativeLayout = (RelativeLayout) inflater.inflate(R.layout.makecon2,container,false);

        initLayout();
        /*
        apart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"아파트 선택",Toast.LENGTH_SHORT).show();
                houseInfoListener.getHouseType("apart");
            }
        });
        villa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"빌라 선택",Toast.LENGTH_SHORT).show();
                houseInfoListener.getHouseType("villa");
            }
        });
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"컨테이너",Toast.LENGTH_SHORT).show();
                houseInfoListener.getHouseType("container");
            }
        });
        guard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"경비실",Toast.LENGTH_SHORT).show();
                houseInfoListener.getHouseType("guard");
            }
        });
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"office",Toast.LENGTH_SHORT).show();
                houseInfoListener.getHouseType("office");
            }
        });
        */
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
        return relativeLayout;
    }
}
