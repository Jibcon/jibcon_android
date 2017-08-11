package com.sm_arts.jibcon.ui.adddevice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.adddevice.phone.AddDevicePhoneFragment;
import com.sm_arts.jibcon.ui.adddevice.product.AddDeviceProductFragment;
import com.sm_arts.jibcon.ui.adddevice.wifi.AddDeviceWifiFragment;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends BaseActivity implements AddDeviceListner {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private int mPageNum;
    private Fragment mAddDevice0;
    private Fragment mAddDevice1;
    private Fragment mAddDevice2;
    private DeviceItem mDeviceItem;

    public void sendDevice() {
        DeviceNetworkHelper.getInstance().postDevice(mDeviceItem,
                (deviceItem) -> {
                    if (deviceItem == null) {
                        Log.d(TAG, "sendDevice: failed to send device. device = " + mDeviceItem.toString());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_adddevice,mAddDevice0).commit();
                    } else {
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

    @Override
    public void nextPage(int num) {
        this.mPageNum += num;
        if (this.mPageNum < 0) {
            this.mPageNum = 0;
        }

        switch (this.mPageNum % 3) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_adddevice,mAddDevice0).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_adddevice,mAddDevice1).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_adddevice,mAddDevice2).commit();
                sendDevice();
                break;
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_adddeviceactivity_activity);
        mAddDevice0 = new AddDeviceProductFragment();
        mAddDevice1 = new AddDeviceWifiFragment();
        mAddDevice2 = new AddDevicePhoneFragment();
        mDeviceItem = new DeviceItem();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_adddevice, mAddDevice0).commit();
    }
}
