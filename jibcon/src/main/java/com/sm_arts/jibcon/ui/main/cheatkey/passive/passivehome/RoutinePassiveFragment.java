package com.sm_arts.jibcon.ui.main.cheatkey.passive.passivehome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.routine.RoutineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-16.
 */

public class RoutinePassiveFragment extends Fragment implements RoutinePassiveView {
    private static final String TAG = "RoutinePassiveFragment";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RoutinePassivePresenter mPassiveRoutinePresenter;
    private RoutinePassiveAdapter mRoutinePassiveAdapter;
    private List<RoutineItem> mRoutineItems;
    private ImageButton mFabPassiveBehindBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_main_routine_passive_fragment, container, false);

        initLayout(root);
        Log.d(TAG, "onCreateView: ");
        mPassiveRoutinePresenter.getPassiveRoutines();
        return root;

    }

    private void initLayout(View root) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.passive_swipe);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.passive_recycler);
        mRoutineItems = new ArrayList<>();
        mRoutinePassiveAdapter = new RoutinePassiveAdapter(mRoutineItems, new RoutineItemlistener() {
            @Override
            public void onItemClicked(View v, int position) {
                RoutineItem routineItem = mRoutinePassiveAdapter.getItemWithPosition(position);
                mPassiveRoutinePresenter.deleteRoutine(routineItem);
            }
        });

        mPassiveRoutinePresenter = new RoutinePassivePresenter(mRoutinePassiveAdapter, this);
        mFabPassiveBehindBtn = (ImageButton) root.findViewById(R.id.fab_passive_behind);
        Log.d(TAG, "onCreateView: ");
        mPassiveRoutinePresenter.getPassiveRoutines();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRoutinePassiveAdapter);
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPassiveRoutinePresenter.getPassiveRoutines();
            }
        });

        mFabPassiveBehindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassiveRoutinePresenter.setOnFabButtonClicked(getActivity());
            }
        });

    }

    @Override
    public void onDataDownloadFinished() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
