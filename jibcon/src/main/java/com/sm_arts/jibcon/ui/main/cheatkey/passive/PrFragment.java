package com.sm_arts.jibcon.ui.main.cheatkey.passive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.data.models.api.dto.RoutineItem;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.PrMakeNew;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 11. 14..
 */

public class PrFragment extends Fragment implements RoutineView{

    NotiData notiData = new NotiData("default","default","default","default","default","default","default");

    @BindView(R.id.swiperefreshlayout_pr_main_simple)
    SwipeRefreshLayout mSwipeRefreshLayout;
    RoutinePresenter mRoutinePresenter;
    @BindView(R.id.recyclerview_pr_main_simple)
    RecyclerView mRecyclerView;
    RoutineAdapter mRoutineAdapter;
    List<RoutineItem> mRoutineItemList;

    @OnClick(R.id.btn_pr_main_simple_makenew) void makeNewPr() {
        Intent intent = new Intent(getActivity(), PrMakeNew.class);
        intent.putExtra("come", notiData);
        startActivity(intent);
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.pr_main_simple, container, false);
        ButterKnife.bind(this,rootView);

        mRoutineItemList = new ArrayList<>();
        mRoutineAdapter = new RoutineAdapter(mRoutineItemList);
        mRoutinePresenter = new RoutinePresenter(this, mRoutineAdapter);
        initlayout();

        return rootView;
    }

    private void initlayout() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRoutinePresenter.getRoutineData();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRoutineAdapter);
        mRecyclerView.setHasFixedSize(true);


    }

    @Override
    public void onDataDownloadFinished() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
