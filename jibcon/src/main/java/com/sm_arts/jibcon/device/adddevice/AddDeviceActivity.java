package com.sm_arts.jibcon.device.adddevice;

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
import com.sm_arts.jibcon.login.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.repository.network.UserService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity implements AddDeviceListner {
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
        //0 : 에어컨
        //1 : 전구
        //2 : 선풍기
        //3 : 냉장고

        if (mDeviceName.equals("에어컨")) {
            mDeviceItem = new DeviceItem(0,mDeviceName);
            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        } else if(mDeviceName.equals("전구")) {
            mDeviceItem = new DeviceItem(1,mDeviceName);
            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        } else if(mDeviceName.equals("선풍기")) {
            mDeviceItem = new DeviceItem(2,mDeviceName);

            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        } else if(mDeviceName.equals("냉장고")) {
            mDeviceItem = new DeviceItem(3,mDeviceName);
            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        }

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
        this.mDeviceCom = deviceCom;
    }


    @Override
    public void setDeviceName(String deviceName) {
        this.mDeviceName = deviceName;
    }

    @Override
    public void setWifi(ScanResult wifi) {
        this.mWifi = wifi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_adddeviceactivity_activity);
        mAddDevice0 = new AddDeviceProductFragment();
        mAddDevice1 = new AddDeviceWifiFragment();
        mAddDevice2 = new AddDevicePhoneFragment();
        mApp = (GlobalApplication) getApplicationContext();

        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,mAddDevice0).commit();
    }

    // for font change
    // TODO: 7/17/17 Base Activity로 바꾸고 attachBaseContext 삭제
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
