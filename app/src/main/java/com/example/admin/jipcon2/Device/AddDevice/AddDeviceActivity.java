package com.example.admin.jipcon2.Device.AddDevice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.jipcon2.Device.DeviceItem;
import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.MainActivity;
import com.example.admin.jipcon2.R;
import com.example.admin.jipcon2.network.ApiService;
import com.example.admin.jipcon2.network.repo;
import com.example.admin.jipcon2.service.DeviceService;
import com.example.admin.jipcon2.service.DeviceServiceImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity implements MakeDeviceListner {

    String DeviceCom;
    String DeviceName;
    //
    String WifiName;
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
            deviceItem.setDeviceWifiAddr("127.0.0.1");
         
        }
        else if(DeviceName.equals("전구")) {
             deviceItem= new DeviceItem(1,DeviceName);

            deviceItem.setDeviceWifiAddr("127.0.0.1");
        }
        else if(DeviceName.equals("선풍기")) {
            deviceItem= new DeviceItem(2,DeviceName);

            deviceItem.setDeviceWifiAddr("127.0.0.1");
        }
        else if(DeviceName.equals("냉장고")) {
            deviceItem= new DeviceItem(3,DeviceName);
            //todo field
            deviceItem.setDeviceWifiAddr("127.0.0.1");
        }

        ApiService apiService = new repo().getService();
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

                    Toast.makeText(getApplicationContext(),"device send fail",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
    public void setWifi(String wifi) {
        this.WifiName=wifi;
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
}
