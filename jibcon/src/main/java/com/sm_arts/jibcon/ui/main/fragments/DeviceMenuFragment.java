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

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.DeviceItem;
import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.ui.floatingbuttonui.FloatingButtonDeviceActivity;
import com.sm_arts.jibcon.ui.main.adapters.DeviceMenuAdapter;

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

    //region Fragment role
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

    private void attachUI() {
        Log.d(TAG, "attachUI: ");
        mFabDeviceBehindBtn = (ImageButton) getView().findViewById(R.id.fab_device_behind);
        mFabDeviceBehindBtn.setOnClickListener(
                v -> mPresenter.fabDeviceBehindBtnClicked()
        );

        mSwiperefreshlayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipelayout_menu_deivce);
        mSwiperefreshlayout.setOnRefreshListener(
                () -> mPresenter.swipeRefreshed()
        );

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.deviceRecyclerView);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        mAdapter = new DeviceMenuAdapter(
                new ArrayList<>(),
                (v, position) -> {
                    Log.d(TAG, "onItemClick: position=[" + position + "]");
                    DeviceItem item = mAdapter.getItemWithPosition(position);
                    mPresenter.deviceItemIvClicked(item);
                },
                (v, position) -> {
                    Log.d(TAG, "onItemClick: position=[" + position + "]");
                    mPresenter.threedotIvClicked(position);
                }
        );


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), GRID_COLUMN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        Log.d(TAG, "loadData: ");
        mPresenter.loadData(deviceItems -> {
            Log.d(TAG, "loadData: Consumer deviceItems=" + deviceItems.toString());
            updateRecyclerView(deviceItems);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.device_devicemenufragment_fragment, container, false);
        Log.d(TAG, "onCreateView: ");

        return root;
    }

    private void detachUI() {
        Log.d(TAG, "detachUI: ");
        // TODO: 7/21/17 implement unbind
    }

    //endregion

    //region View role
    public void gotoFloatingButtonDeviceActivity() {
        Log.d(TAG, "gotoFloatingButtonDeviceActivity: ");
        startActivity(new Intent(getActivity().
                getApplicationContext(), FloatingButtonDeviceActivity.class));
    }

    @Override
    public void setSwiperefreshingOff() {
        Log.d(TAG, "setSwiperefreshingOff: ");
        mSwiperefreshlayout.setRefreshing(false);
    }

    @Override
    public void refreshDeviceItems(List<DeviceItem> deviceItems) {
        Log.d(TAG, "refreshDeviceItems() called with: deviceItems = [" + deviceItems + "]");
        updateRecyclerView(deviceItems);
    }

    public void showDeviceDialog() {
        Log.d(TAG, "showDeviceDialog: ");
        DeviceDialog deviceDialog = new DeviceDialog(getContext());
        deviceDialog.show();
    }

    private void updateRecyclerView(List<DeviceItem> deviceItems) {
        Log.d(TAG, "updateRecyclerView() called with: " +
                "deviceItems = [" + deviceItems + "]");
        mAdapter.setDeviceItems(deviceItems);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void updateDevicesOnOffState() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public DeviceMenuAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void showContent(int position, String con) {
        mAdapter.showContent(position, con);
    }

    //endregion

}
