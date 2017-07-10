package com.sm_arts.jibcon.app.conshop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

/**
 * Created by user on 2017-03-30.
 */

public class ConshopMenuActivity extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    public ConshopMenuActivity(){}

    TextView mBest;
    TextView mHome;
    TextView mHealth;
    TextView mOutdoor;
    private  void initLayout(View view)
    {
        mBest=(TextView)view.findViewById(R.id.Txt_Market_Best);
        mHome=(TextView)view.findViewById(R.id.Txt_Market_Home);
        mHealth=(TextView)view.findViewById(R.id.Txt_Market_Health);
        mOutdoor=(TextView)view.findViewById(R.id.Txt_Market_Outdoor);

        mBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Move to Best");
//                Toast.makeText(getActivity().getApplicationContext(),"Move to Best",Toast.LENGTH_SHORT).show();
            }
        });

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Move to Home");
//                Toast.makeText(getActivity().getApplicationContext(),"Move to Home",Toast.LENGTH_SHORT).show();

            }
        });
        mHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Move to Health");
//                Toast.makeText(getActivity().getApplicationContext(),"Move to Health",Toast.LENGTH_SHORT).show();

            }
        });
        mOutdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Move to Ourdoor");
//                Toast.makeText(getActivity().getApplicationContext(),"Move to Ourdoor",Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 주의 : menu_market의 최상단 레이아웃은 scrollview이기 객체 생성시에도 타입을 ScrollView로 해야됨.
        ScrollView layout = (ScrollView) inflater.inflate(R.layout.menu_market, container, false);
        initLayout(layout);
        return layout;
    }
}
