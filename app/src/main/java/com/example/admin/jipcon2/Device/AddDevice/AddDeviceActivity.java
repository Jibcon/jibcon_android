package com.example.admin.jipcon2.Device.AddDevice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.jipcon2.Device.DeviceItem;
import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.MainActivity;
import com.example.admin.jipcon2.R;

import java.util.ArrayList;

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
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //장치 추가하고 메인으로
                        ArrayList<DeviceItem> arr = app.getDeviceItemArrayList() ;
                        //0 : 에어컨
                        //1 : 전구
                        //2 : 선풍기
                        //3 : 냉장고
                        if(DeviceName.equals("에어컨"))
                        {
                            arr.add(new DeviceItem(0,DeviceName));

                        }
                        else if(DeviceName.equals("전구"))
                            arr.add(new DeviceItem(1,DeviceName));
                        else if(DeviceName.equals("선풍기"))
                            arr.add(new DeviceItem(2,DeviceName));
                        else if(DeviceName.equals("냉장고"))
                            arr.add(new DeviceItem(3,DeviceName));

                        app.setDeviceItemArrayList(arr);
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
