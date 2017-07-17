package com.sm_arts.jibcon.device.adddevice;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.main.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.utils.network.RetrofitUtils;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity implements AddDeviceListner {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    String mDeviceCom;
    String mDeviceName;
    //
    ScanResult mWifi;
    int mPageNum;
    Fragment mAddDevice0;
    Fragment mAddDevice1;
    Fragment mAddDevice2;
    GlobalApplication mApp;
//    ArrayList<DeviceItem> arr;
    DeviceItem mDeviceItem;
    public void sendDevice()
    {
//       arr = mApp.getDeviceItemArrayList() ;
        //0 : 에어컨
        //1 : 전구
        //2 : 선풍기
        //3 : 냉장고
//        String deviceType;//디바이스 메뉴의 int 값. int 값으로 판별
//        String deviceName;//디바이스 메뉴 아이템 이름 ex) 전등 알람 등등..
//        String deviceWifiAddr;
//        String id;
//        String deviceCom;
//        boolean deviceOnOffState;
//        String user;
        if(mDeviceName.equals("에어컨"))
        {
            mDeviceItem= new DeviceItem(0,mDeviceName);
            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
         
        }
        else if(mDeviceName.equals("전구")) {
             mDeviceItem= new DeviceItem(1,mDeviceName);

            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        }
        else if(mDeviceName.equals("선풍기")) {
            mDeviceItem= new DeviceItem(2,mDeviceName);

            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        }
        else if(mDeviceName.equals("냉장고")) {
            mDeviceItem= new DeviceItem(3,mDeviceName);
            mDeviceItem.setDeviceWifiAddr(getWifiAddr());
        }

        ApiService apiService = (ApiService) RetrofitUtils.getInstance().getService(ApiService.class);
        Log.d(TAG, "sendDevice: Call.enqueue DeviceItem "+mDeviceItem.toString());

        Call<DeviceItem> c = apiService.addDevice("Token " +mApp.getUserToken(),mDeviceItem);
        Log.d("TAG", "sendDevice: "+c.toString());

        try{
            c.enqueue(new Callback<DeviceItem>() {
                @Override
                public void onResponse(Call<DeviceItem> call, Response<DeviceItem> response) {
                    DeviceServiceImpl.getInstance().notifyDeviceItemsChanged();
//                    DeviceServiceImpl.getInstance().reloadDeviceItems();

//                    arr= mApp.getDeviceItemArrayList();

//                    arr.add(response.body());
//                    mApp.setDeviceItemArrayList(arr);

                }

                @Override
                public void onFailure(Call<DeviceItem> call, Throwable t) {
                    Log.d(TAG, "onFailure: device send fail");
//                    Toast.makeText(getApplicationContext(),"device send fail",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
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
    public void NextPage(int num) {
        this.mPageNum+=num;
        if(this.mPageNum<0)
            this.mPageNum=0;
        switch (this.mPageNum%3)
        {
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
        this.mDeviceCom=deviceCom;
    }

    @Override
    public void setDeviceName(String deviceName) {
        this.mDeviceName=deviceName;
    }

    @Override
    public void setWifi(ScanResult wifi) {
        this.mWifi=wifi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_add_device_activity);
        mAddDevice0=new AddDeviceProductFragment();
        mAddDevice1=new AddDeviceWifiFragment();
        mAddDevice2=new AddDevicePhoneFragment();
        mApp=(GlobalApplication)getApplicationContext();

        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,mAddDevice0).commit();

    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
