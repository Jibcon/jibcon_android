package com.sm_arts.jibcon.app.getothercon;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-05-15.
 * 집콘 초대 받기
 *
 */

public class GetOtherConActivity extends BaseActivity{
    private RecyclerView mRecyclerView;


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<getConData> mGetConDataset;

    Button mBtnGotoMain;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getothercon);

        mRecyclerView = (RecyclerView) findViewById(R.id.getcon_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mGetConDataset = new ArrayList<>();
        mAdapter = new GetOtherConAdapter(mGetConDataset);
        mRecyclerView.setAdapter(mAdapter);

        //데이터 입력
        mGetConDataset.add(new getConData(R.drawable.default_user, "찬주의 자취방",
                "example@jibcon.com", "환영합니다!"));

        mBtnGotoMain = (Button)findViewById(R.id.btn_gotoMain); // 등록 완료
        mBtnGotoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetOtherConActivity.this, GetOtherConFinishActivity.class);
                startActivity(intent);
            }
        });

    }

}
