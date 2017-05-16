package com.sm_arts.jibcon.GetOtherCon;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

/**
 * Created by user on 2017-05-15.
 * 집콘 초대 받기
 *
 */

public class GetOtherCon extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<getConData> getConDataset;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getothercon);

        mRecyclerView = (RecyclerView) findViewById(R.id.getcon_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getConDataset = new ArrayList<>();
        mAdapter = new GetConAdapter(getConDataset);
        mRecyclerView.setAdapter(mAdapter);

        //데이터 입력
        getConDataset.add(new getConData(R.drawable.default_user, "찬주의 자취방",
                "example@jibcon.com", "환영합니다!"));
    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
