package com.sm_arts.jibcon.ui.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.ui.main.adapters.DeviceMenuAdapter;
import com.sm_arts.jibcon.device.DeviceMenuGridView;
import com.sm_arts.jibcon.device.service.DeviceService;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.floatingbuttonui.FloatingButtonDeviceActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuFragment extends Fragment {
    private static final String TAG = "DeviceMenuFragment";

    SwipeRefreshLayout mSwiperefreshlayout;

    //Button button;
    private RecyclerView mRecyclerView;
    private DeviceMenuAdapter mAdapter;
    private ImageButton mFabDeviceBehindBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachUI();
    }

    @Override
    public void onDestroyView() {
        detachUI();
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void detachUI() {

    }

    private void attachUI() {
        mFabDeviceBehindBtn = (ImageButton) getView().findViewById(R.id.fab_device_behind);

        mFabDeviceBehindBtn.setOnClickListener(
                v -> startActivity(new Intent(getActivity().
                        getApplicationContext(),FloatingButtonDeviceActivity.class))
        );

        mSwiperefreshlayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipelayout_menu_deivce);

        mSwiperefreshlayout.setOnRefreshListener(
                () ->
                        DeviceServiceImpl.getInstance().reloadDeviceItems(
                                (deviceItems) -> {
                                    mAdapter.setDeviceItems((ArrayList)deviceItems);
                                    mAdapter.notifyDataSetChanged();
                                    mSwiperefreshlayout.setRefreshing(false);
                                }
                        )
        );

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.deviceRecyclerView);

        mAdapter = new DeviceMenuAdapter(
                new ArrayList<DeviceItem>(),
                (v, position) ->
                        Log.d(TAG, "onItemClick: position=[" + position + "]")
        );
        mRecyclerView.setAdapter(mAdapter);

        Log.d(TAG, "onCreateView: ");
        DeviceServiceImpl.getInstance().getDeviceItems(
                deviceItems -> {
                    Log.d(TAG, "onSuccessGetDeviceItems: ");
                    mAdapter.setDeviceItems((ArrayList) deviceItems);
                    mAdapter.notifyDataSetChanged();
                }
        );
    }

    private void loadData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.device_devicemenufragment_fragment, container, false);
        Log.d(TAG, "onCreateView: ");

        return root;
    }

}
