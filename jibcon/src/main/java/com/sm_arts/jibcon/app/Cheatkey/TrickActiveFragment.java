package com.sm_arts.jibcon.app.Cheatkey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.sm_arts.jibcon.ui.FloatingButtonUi.*;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by WooJin & ChanJoo on 2017-04-14.
 */

public class TrickActiveFragment extends android.support.v4.app.Fragment{
    // For Recyclerview(CardView)
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ActiveCheatkeyData> activeCheatkeyDataset;

    Context context;

    public TrickActiveFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.cheatkey_active, container, false);

        // 햄버거 버튼
        ImageButton imageButton =(ImageButton)rootView.findViewById(R.id.fab_cheatkey_active_behind);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),FlotingButtonActiveActivity.class));
            }
        });

        // RecyclerView 구현 및 Adpater 설정
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.active_cardview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(context,2); // RecyclerView로 GridView 구현
        mRecyclerView.setLayoutManager(mLayoutManager);

        activeCheatkeyDataset = new ArrayList<>();
        mAdapter = new ActiveCheatkeyAdapter(activeCheatkeyDataset);
        mRecyclerView.setAdapter(mAdapter);

        // 치트키 수동 입력, 추후 사용자가 치트키 등록시 추가되도록 구현해야
        // cardview #1
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting,R.drawable.blank,"출근"));
        // cardview #2
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting,R.drawable.blank, "퇴근"));
        // cardview #3
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, R.drawable.blank, "굿나잇"));
        // cardview #4
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, R.drawable.blank, "출장 & 휴가"));
        // cardview #5
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, R.drawable.blank, "등교"));
        // cardview #6
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, R.drawable.blank, "아침 기상"));
        // cardview #7
        activeCheatkeyDataset.add(new ActiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, R.drawable.blank, "현호 재울 때"));


        return rootView;
    }
}
