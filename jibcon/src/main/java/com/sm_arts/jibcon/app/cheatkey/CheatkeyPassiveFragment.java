package com.sm_arts.jibcon.app.cheatkey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.ui.floatingbuttonui.FloatingButtonPassiveActivity;
import com.sm_arts.jibcon.R;
import java.util.ArrayList;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by WooJin on 2017-04-14.
 */

public class CheatkeyPassiveFragment extends android.support.v4.app.Fragment{

    @BindString(R.string.trick_passive_input_menu_1) String input1;
    @BindString(R.string.trick_passive_input_menu_2) String input2;
    @BindString(R.string.trick_passive_input_menu_3) String input3;
    @BindString(R.string.trick_passive_input_menu_4) String input4;

    @BindString(R.string.trick_passive_output_menu_1) String output1;
    @BindString(R.string.trick_passive_output_menu_2) String output2;
    @BindString(R.string.trick_passive_output_menu_3) String output3;
    @BindString(R.string.trick_passive_output_menu_4) String output4;

    
    //hamburger btn
    @OnClick(R.id.fab_cheatkey_passive_behind) void fab_cheatkey_passive_behind(){
        startActivity(new Intent(getContext(), FloatingButtonPassiveActivity.class));
    }

    // For Recyclerview(CardView)
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PassiveCheatkeyData> mPassiveCheatkeyDataset;

    Context mContext;

    public CheatkeyPassiveFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.cheatkey_passive_fragment, container, false); // 액티비티에서는 setContentView
        ButterKnife.bind(this, v);


        // RecyclerView 구현 및 Adpater 설정
        mRecyclerView = (RecyclerView)v.findViewById(R.id.passive_cardview);
        mRecyclerView.setHasFixedSize(true);

        //mLayoutManager = new GridLayoutManager(context,2); // RecyclerView로 GridView 구현 test
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPassiveCheatkeyDataset = new ArrayList<>();
        mAdapter = new PassiveCheatkeyAdapter(mPassiveCheatkeyDataset);
        mRecyclerView.setAdapter(mAdapter);


        // 치트키 수동 입력, 추후 사용자가 치트키 등록시 추가되도록 구현해야
        // cardview #1
        mPassiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting,input1, output1));
        // cardview #2
        mPassiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, input2, output2));
        // cardview #3
        mPassiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting, input3, output3));
        // cardview #4
        mPassiveCheatkeyDataset.add(new PassiveCheatkeyData
                (R.id.btn_passive_cheatkey_setting,input4, output4));

        return v;
    }
}
