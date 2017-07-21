package com.sm_arts.jibcon.ui.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.ui.main.adapters.DeviceMenuAdapter;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.floatingbuttonui.FloatingButtonDeviceActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuFragment extends Fragment implements DeviceMenuView {
    private static final String TAG = "DeviceMenuFragment";
    private static final int GRID_COLUMN_COUNT = 2;

    private SwipeRefreshLayout mSwiperefreshlayout;
    private RecyclerView mRecyclerView;
    private DeviceMenuAdapter mAdapter;
    private ImageButton mFabDeviceBehindBtn;
    private DeviceMenuPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");
        mPresenter = new DeviceMenuPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: ");
        attachUI();
        loadData();
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
                v -> gotoFloatingButtonDeviceActivity()
        );

        mSwiperefreshlayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipelayout_menu_deivce);

        mSwiperefreshlayout.setOnRefreshListener(
                () ->
                        DeviceServiceImpl.getInstance().reloadDeviceItems(
                                (deviceItems) -> {
                                    mAdapter.setDeviceItems((ArrayList) deviceItems);
                                    mAdapter.notifyDataSetChanged();
                                    mSwiperefreshlayout.setRefreshing(false);
                                }
                        )
        );

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.deviceRecyclerView);

        initializeRecyclerView();
    }

    private void gotoFloatingButtonDeviceActivity() {
        Log.d(TAG, "gotoFloatingButtonDeviceActivity: ");
        startActivity(new Intent(getActivity().
                getApplicationContext(), FloatingButtonDeviceActivity.class));
    }

    private void initializeRecyclerView() {
        mAdapter = new DeviceMenuAdapter(
                new ArrayList<>(),
                (v, position) -> {
                    Log.d(TAG, "onItemClick: position=[" + position + "]");
                    deviceItemIvClicked(position);
                },
                (v, position) -> {
                    Log.d(TAG, "onItemClick: position=[" + position + "]");
                    threedotIvClicked(position);
                }
        );

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), GRID_COLUMN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void deviceItemIvClicked(int position) {
        Log.d(TAG, "deviceItemIvClicked() called with: position = [" + position + "]");
        mPresenter.deviceItemIvClicked(position);
    }

    public void showDeviceDialog() {
        Log.d(TAG, "showDeviceDialog: ");
        DeviceDialog deviceDialog = new DeviceDialog(getContext());
        deviceDialog.show();
    }

    private void threedotIvClicked(int position) {
        Log.d(TAG, "threedotIvClicked() called with: position = [" + position + "]");
        mPresenter.threedotIvClicked(position);
    }

    private void loadData() {
        Log.d(TAG, "loadData: ");
        mPresenter.loadData(deviceItems -> {
            Log.d(TAG, "loadData: Consumer deviceItems=" + deviceItems.toString());
            updateRecyclerView(deviceItems);
        });
    }

    private void updateRecyclerView(List<DeviceItem> deviceItems) {
        Log.d(TAG, "updateRecyclerView() called with: " +
                "deviceItems = [" + deviceItems + "]");
        mAdapter.setDeviceItems(deviceItems);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.device_devicemenufragment_fragment, container, false);
        Log.d(TAG, "onCreateView: ");

        return root;
    }

}
