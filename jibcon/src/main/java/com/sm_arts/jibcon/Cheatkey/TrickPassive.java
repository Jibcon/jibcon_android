package com.sm_arts.jibcon.Cheatkey;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sm_arts.jibcon.R;
import java.util.ArrayList;


/**
 * Created by WooJin on 2017-04-14.
 */

public class TrickPassive extends android.support.v4.app.Fragment{
    // For Recyclerview(CardView)
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PassiveCheatkeyData> passiveCheatkeyDataset;

    Context context;

    public TrickPassive(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.cheatkey_passive, container, false); // 액티비티에서는 setContentView

        mRecyclerView = (RecyclerView)v.findViewById(R.id.passive_cardview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        passiveCheatkeyDataset = new ArrayList<>();
        mAdapter = new PassiveCheatkeyAdapter(passiveCheatkeyDataset);
        mRecyclerView.setAdapter(mAdapter);

        // 치트키 수동 입력, 추후 사용자가 치트키 등록시 추가되도록 구현해야
        // cardview #1
        passiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting,"온도가 30도 이상이 되면","에어컨 온도를 22도에 맞춰 켜기"));
        // cardview #2
        passiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, "아침에 비가오면", "평소보다 30분 전에 알람 울리기"));
        // cardview #3
        passiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, "퇴근 하고 집에 오는 동안", "집안 온도 조절 및\n공기청정기 가동하기"));
        // cardview #4
        passiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, "출근 하면", "가스 벨브 잠그고\n방범 시스템 가동하기"));

        return v;
    }
    // onViewCreated 추가할까?
}
