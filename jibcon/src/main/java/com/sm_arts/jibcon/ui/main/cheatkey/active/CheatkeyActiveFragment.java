package com.sm_arts.jibcon.ui.main.cheatkey.active;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.sm_arts.jibcon.ui.additional.floatingbuttonui.*;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by WooJin & ChanJoo on 2017-04-14.
 */

public class CheatkeyActiveFragment extends android.support.v4.app.Fragment {
    // For Recyclerview(CardView)
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ActiveCheatkeyData> mActiveCheatkeyDataset;

    Context mContext;

    public CheatkeyActiveFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.maincheatkeyactive_activecheatkey_fragment, container, false);

        // 햄버거 버튼
        ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.fab_cheatkey_active_behind);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FloatingButtonActiveActivity.class));
            }
        });

        // RecyclerView 구현 및 Adpater 설정
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.active_cardview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(mContext, 1); // RecyclerView로 GridView 구현
        mRecyclerView.setLayoutManager(mLayoutManager);

        mActiveCheatkeyDataset = new ArrayList<>();
        mAdapter = new ActiveCheatkeyAdapter(mActiveCheatkeyDataset);
        mRecyclerView.setAdapter(mAdapter);

        // 치트키 수동 입력, 추후 사용자가 치트키 등록시 추가되도록 구현해야
        // cardview #1
        mActiveCheatkeyDataset.add(new ActiveCheatkeyData(
                R.drawable.blank,"출근"
        ));


        return rootView;
    }
}
