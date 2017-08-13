package com.sm_arts.jibcon.ui.adddevice.wifi;

import android.Manifest;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.databinding.AdddeviceWifiFragmentBinding;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.ui.adddevice.wifi.adapter.WifiAdapter;
import com.sm_arts.jibcon.ui.adddevice.wifi.viewmodels.WifiViewModel;
import com.sm_arts.jibcon.utils.broadcastreceiver.WifiscanManager;
import com.sm_arts.jibcon.utils.helper.PermissionHelper;


/**
 * Created by admin on 2017-04-15.
 */

public class WifiFragment extends LifecycleFragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private AddDeviceListner mMakeDeviceListener;
    private WifiViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        AdddeviceWifiFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.adddevice_wifi_fragment,
                        container, false);
        View v = binding.getRoot();
        mViewModel = ViewModelProviders.of(this).get(WifiViewModel.class);
        binding.setWifiviewmodel(mViewModel);
        initView(binding);

        chkPermissionForScanWifi();
        return v;
    }

    private void initView(AdddeviceWifiFragmentBinding binding) {
        Log.d(TAG, "initView: ");

        binding.recyclerviewWifilist.setAdapter(
                new WifiAdapter());
        binding.recyclerviewWifilist.setLayoutManager(
                new LinearLayoutManager(getContext()));

        binding.btnNext.setOnClickListener(
                (btn) -> mMakeDeviceListener.nextPage(this));
    }

    private void chkPermissionForScanWifi() {
        PermissionHelper.chkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                this,
                PermissionHelper.ACCESSCOARSELOCATION_REQUEST_CODE,
                this::initWifiManager);
    }

    private void initWifiManager() {
        Log.d(TAG, "initWifiManager: ");
        WifiscanManager.init(getContext());
        mViewModel.onWifireceiverInitialized(WifiscanManager.getInstance());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: requestCode = " + requestCode);

        if (requestCode == PermissionHelper.ACCESSCOARSELOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_GRANTED");
                initWifiManager();
            } else {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_DENIED");
                chkPermissionForScanWifi();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
        mMakeDeviceListener = (AddDeviceListner) context;
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
        mMakeDeviceListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
