package com.sm_arts.jibcon.ui.adddevice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.adddevice.product.AddPhilipsHueFragment;
import com.sm_arts.jibcon.ui.adddevice.product.ProductFragment;
import com.sm_arts.jibcon.ui.adddevice.product.ProgressFragment;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;

import java.util.ArrayList;
import java.util.List;

public class AddDeviceActivity extends BaseActivity implements AddDeviceListner {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private List<Fragment> mFragments = new ArrayList<>();
    private DeviceItem mDeviceItem;
    private int mWifiFragmentIdx;

    public void sendDevice() {
        DeviceNetworkHelper.getInstance().postDevice(mDeviceItem,
                (deviceItem) -> {
                    if (deviceItem == null) {
                        Log.d(TAG, "sendDevice: failed to send device. device = " + mDeviceItem.toString());
                        //goFirstPage();

                    } else {
                        Log.d(TAG, "sendDevice: success to send device");
                        Handler handler = new Handler();
                        handler.postDelayed(
                                () -> {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }, 1500);
                    }
                });
    }

    private void goPage(int i) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_adddevice, mFragments.get(i),mFragments.getClass().getName()).commit();
    }

    @Override
    public void nextPage(@NonNull final Fragment fragment) {
        final int page = mFragments.indexOf(fragment);
        if (page == (mFragments.size() - 1)) {

            // last page

        } else if (page == (mFragments.size() - 2)) {
            // last-1 page
            sendDevice();

        }

        final int nextPage = (page + 1) % mFragments.size();

        goPage(nextPage);
    }

    @Override
    public void setData(LinkedTreeMap<String, Object> data) {
        mDeviceItem.setData(data);
    }

    @Override
    public void setDeviceCom(String deviceCom) {
        mDeviceItem.setDeviceCom(deviceCom);
    }

    @Override
    public void setDeviceName(String deviceName) {
        mDeviceItem.setDeviceName(deviceName);
    }

    @Override
    public void setDeviceType(String deviceType) {
        mDeviceItem.setDeviceType(deviceType);
    }

    @Override
    public void setRoomName(String roomName) {
        mDeviceItem.setRoomName(roomName);
    }

    @Override
    public void setAeName(String aeName) {
        mDeviceItem.setAeName(aeName);
    }

    @Override
    public void setCntName(String cntName) {
        mDeviceItem.setCntName(cntName);
    }


    @Override
    public void setUserId(String userId) {
        mDeviceItem.setUser(JibconLoginManager.getInstance().getUserId());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddevice_adddevice_activity);
        mFragments.add(new ProductFragment());
        mFragments.add(new AddPhilipsHueFragment());
        mFragments.add(new ProgressFragment());
        mDeviceItem = new DeviceItem();

        goFirstPage();
    }

    private void goFirstPage() {
        goPage(0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mFragments.get(mWifiFragmentIdx).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
