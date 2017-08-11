package com.sm_arts.jibcon.ui.main.cheatkey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.ui.additional.floatingbuttonui.FloatingButtonPassiveActivity;
import com.sm_arts.jibcon.R;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by WooJin on 2017-04-14.
 */

public class CheatkeyPassiveFragment extends android.support.v4.app.Fragment{
    //hamburger btn
    @OnClick(R.id.fab_cheatkey_passive_behind) void fab_cheatkey_passive_behind() {
        startActivity(new Intent(getContext(), FloatingButtonPassiveActivity.class));
    }

    // For Recyclerview(CardView)
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PassiveCheatkeyData> mPassiveCheatkeyDataset;

    Context mContext;

    public CheatkeyPassiveFragment() {}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.cheatkey_cheatkeypassivefragment_fragment, container, false); // 액티비티에서는 setContentView
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

        for(int i = 0; i < 4; i++) {
            mPassiveCheatkeyDataset.add(
                    new PassiveCheatkeyData
                            (R.id.btn_passive_cheatkey_setting,
                                    getResources().getStringArray(R.array.trick_passive_input_array)[i],
                                    getResources().getStringArray(R.array.trick_passive_output_array)[i]));
        }

        return v;
    }
}
