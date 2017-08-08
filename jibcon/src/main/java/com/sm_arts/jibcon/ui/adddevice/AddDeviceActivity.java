package com.sm_arts.jibcon.ui.adddevice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.network.api.DeviceService;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.adddevice.phone.AddDevicePhoneFragment;
import com.sm_arts.jibcon.ui.adddevice.wifi.AddDeviceWifiFragment;
import com.sm_arts.jibcon.ui.adddevice.product.AddDeviceProductFragment;
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
        /*set device state on*/
        mDeviceItem.setDeviceOnOffState(true);
        DeviceService deviceService = RetrofitClients.getInstance().getService(DeviceService.class);
        Log.d(TAG, "sendDevice: Call.enqueue DeviceItem " + mDeviceItem.toString());
      
        Call<DeviceItem> call = deviceService.addDevice(
                JibconLoginManager.getInstance().getUserTokenAsHeader(), mDeviceItem);
        Log.d("TAG", "sendDevice: " + call.toString());

        try {
            call.enqueue(new Callback<DeviceItem>() {
                @Override
                public void onResponse(Call<DeviceItem> call, Response<DeviceItem> response) {

                }

                @Override
                public void onFailure(Call<DeviceItem> call, Throwable t) {
                    Log.d(TAG, "onFailure: device send fail");
//                    Toast.makeText(getApplicationContext(),"device send fail",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //장치 추가하고 메인으로
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };

                Handler handler=new Handler();
                handler.postDelayed(runnable,1500);
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
