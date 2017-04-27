package com.example.admin.jipcon2;

import android.app.Activity;
import android.app.Application;

import com.example.admin.jipcon2.Device.DeviceItem;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 2017-04-06.
 */

public class GlobalApplication extends Application{

    //모든 액티비티에서 공유할 수 있는 정보만 담기 최대 4KB..?

    private static volatile GlobalApplication obj = null;
    private static volatile Activity currentActivity = null;
    //카톡 로그인

    String userToken;
    ArrayList<DeviceItem> deviceItemArrayList;//device 메뉴 아이템들의 리스트
    String username;
    String userEmail;
    URL userProfileImage;

    public URL getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(URL userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        deviceItemArrayList = new ArrayList<>();
        username="TestUser";
        userEmail="Jipcon@Jipcon.com";
//        KakaoSDK.init(new KaKaoSDKAdpater());
        //카톡로그인

    }

    public ArrayList<DeviceItem> getDeviceItemArrayList() {
        return deviceItemArrayList;
    }

    public void setDeviceItemArrayList(ArrayList<DeviceItem> deviceItemArrayList) {
        this.deviceItemArrayList = deviceItemArrayList;

    }


    //카톡 로그인용
    public static GlobalApplication getGlobalApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

}
