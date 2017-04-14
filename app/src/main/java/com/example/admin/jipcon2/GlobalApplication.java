package com.example.admin.jipcon2;

import android.app.Application;

import com.example.admin.jipcon2.Device.DeviceItem;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-06.
 */

public class GlobalApplication extends Application{

    //모든 액티비티에서 공유할 수 있는 정보만 담기 최대 4KB..?


    ArrayList<DeviceItem> deviceItemArrayList;//device 메뉴 아이템들의 리스트
    String username;
    String userEmail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        deviceItemArrayList = new ArrayList<>();
        username="Test User";
        userEmail="Jipcon@Jipcon.com";
    }

    public ArrayList<DeviceItem> getDeviceItemArrayList() {
        return deviceItemArrayList;
    }

    public void setDeviceItemArrayList(ArrayList<DeviceItem> deviceItemArrayList) {
        this.deviceItemArrayList = deviceItemArrayList;

    }
}
