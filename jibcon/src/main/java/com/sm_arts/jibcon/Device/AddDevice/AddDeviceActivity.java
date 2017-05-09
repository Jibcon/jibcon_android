package com.sm_arts.jibcon.Device.AddDevice;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sm_arts.jibcon.Device.DeviceItem;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.network.repo;
import com.sm_arts.jibcon.Device.service.DeviceServiceImpl;
import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity implements MakeDeviceListner {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    String DeviceCom;
    String DeviceName;
    //
    ScanResult Wifi;
    int pageNum;
    Fragment addDevice0;
    Fragment addDevice1;
    Fragment addDevice2;
    GlobalApplication app;
//    ArrayList<DeviceItem> arr;
    DeviceItem deviceItem;
    public void sendDevice()
    {
//       arr = app.getDeviceItemArrayList() ;
        //0 : 에어컨
        //1 : 전구
        //2 : 선풍기
        //3 : 냉장고
        String deviceType;//디바이스 메뉴의 int 값. int 값으로 판별
        String deviceName;//디바이스 메뉴 아이템 이름 ex) 전등 알람 등등..
        String deviceWifiAddr;
        String id;
        String deviceCom;
        boolean deviceOnOffState;
        String user;
        if(DeviceName.equals("에어컨"))
        {
            deviceItem= new DeviceItem(0,DeviceName);
            deviceItem.setDeviceWifiAddr(getWifiAddr());
         
        }
        else if(DeviceName.equals("전구")) {
             deviceItem= new DeviceItem(1,DeviceName);

            deviceItem.setDeviceWifiAddr(getWifiAddr());
        }
        else if(DeviceName.equals("선풍기")) {
            deviceItem= new DeviceItem(2,DeviceName);

            deviceItem.setDeviceWifiAddr(getWifiAddr());
        }
        else if(DeviceName.equals("냉장고")) {
            deviceItem= new DeviceItem(3,DeviceName);
            deviceItem.setDeviceWifiAddr(getWifiAddr());
        }

        ApiService apiService = new repo().getService();
        Log.d(TAG, "sendDevice: Call.enqueue DeviceItem "+deviceItem.toString());
        Call<DeviceItem> c = apiService.addDevice("Token " +app.getUserToken(),deviceItem);
        Log.d("TAG", "sendDevice: "+c.toString());

        try{
            c.enqueue(new Callback<DeviceItem>() {
                @Override
                public void onResponse(Call<DeviceItem> call, Response<DeviceItem> response) {
                    DeviceServiceImpl.getInstance().notifyDeviceItemsChanged();
//                    DeviceServiceImpl.getInstance().reloadDeviceItems();

//                    arr= app.getDeviceItemArrayList();

//                    arr.add(response.body());
//                    app.setDeviceItemArrayList(arr);

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
        if (Wifi == null) {
            return "127.0.0.1";
        } else {
            return Wifi.BSSID;
        }
    }

    @Override
    public void NextPage(int num) {
        this.pageNum+=num;
        if(this.pageNum<0)
            this.pageNum=0;
        switch (this.pageNum%3)
        {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,addDevice0).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,addDevice1).commit();

                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,addDevice2).commit();

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
        this.DeviceCom=deviceCom;
    }

    @Override
    public void setDeviceName(String deviceName) {
        this.DeviceName=deviceName;
    }

    @Override
    public void setWifi(ScanResult wifi) {
        this.Wifi=wifi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        addDevice0=new AddDevice0();
        addDevice1=new AddDevice1();
        addDevice2=new AddDevice2();
        app=(GlobalApplication)getApplicationContext();

        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_addDevice,addDevice0).commit();

    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
