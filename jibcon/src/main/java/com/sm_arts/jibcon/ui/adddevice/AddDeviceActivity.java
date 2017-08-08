package com.sm_arts.jibcon.ui.adddevice;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;
import com.sm_arts.jibcon.data.repository.helper.DeviceServiceImpl;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends BaseActivity implements AddDeviceListner {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private String mDeviceCom;
    private String mDeviceName;
    private ScanResult mWifi;
    private int mPageNum;
    private Fragment mAddDevice0;
    private Fragment mAddDevice1;
    private Fragment mAddDevice2;
    private GlobalApplication mApp;
    private DeviceItem mDeviceItem;

    public void sendDevice() {



        /*set device state on*/
        mDeviceItem.setDeviceOnOffState(true);
        UserService userService = RetrofiClients.getInstance().getService(UserService.class);
        Log.d(TAG, "sendDevice: Call.enqueue DeviceItem "+mDeviceItem.toString());
      
        Call<DeviceItem> c = userService.addDevice("Token " +
                JibconLoginManager.getInstance().getUserToken(), mDeviceItem);
        Log.d("TAG", "sendDevice: " + c.toString());

        try {
            c.enqueue(new Callback<DeviceItem>() {
                @Override
                public void onResponse(Call<DeviceItem> call, Response<DeviceItem> response) {
                    DeviceServiceImpl.getInstance().notifyDeviceItemsChanged();

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

    private String getWifiAddr() { // todo implements
        if (mWifi == null) {
            return "127.0.0.1";
        } else {
            return mWifi.BSSID;
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
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,mAddDevice0).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,mAddDevice1).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,mAddDevice2).commit();
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

    }

    @Override
    public void setAeName(String aeName) {

    }

    @Override
    public void setCntName(String setCntName) {

    }

    @Override
    public void setContent(String content) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_adddeviceactivity_activity);
        mAddDevice0 = new AddDeviceProductFragment();
        mAddDevice1 = new AddDeviceWifiFragment();
        mAddDevice2 = new AddDevicePhoneFragment();
        mApp = (GlobalApplication) getApplicationContext();
        mDeviceItem = new DeviceItem();

        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,mAddDevice0).commit();
    }
}