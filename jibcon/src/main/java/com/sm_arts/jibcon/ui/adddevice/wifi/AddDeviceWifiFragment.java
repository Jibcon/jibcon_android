package com.sm_arts.jibcon.ui.adddevice.wifi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.utils.helper.PermissionHelper;

import static com.sm_arts.jibcon.utils.helper.PermissionHelper.ACCESSCOARSELOCATION_REQUEST_CODE;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDeviceWifiFragment extends Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private AddDeviceListner mMakeDeviceListener;

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

    private void initlayout() {
        Log.d(TAG, "initlayout: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View v = inflater.inflate(R.layout.device_adddevicewififragment_fragment,container,false);

        initlayout();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        chkPermissionForScanWifi();
    }

    private void initWifiManager() {
        Log.d(TAG, "initWifiManager: ");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: requestCode = " + requestCode);

        if (requestCode == ACCESSCOARSELOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_GRANTED");
                initWifiManager();
            } else {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_DENIED");
                chkPermissionForScanWifi();
            }
        }
    }

    private void chkPermissionForScanWifi() {
        PermissionHelper.chkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                this,
                PermissionHelper.ACCESSCOARSELOCATION_REQUEST_CODE,
                this::initWifiManager);
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
