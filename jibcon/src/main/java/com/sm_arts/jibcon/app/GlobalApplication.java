package com.sm_arts.jibcon.app;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.kakao.auth.KakaoSDK;
import com.nhn.android.naverlogin.OAuthLogin;
import com.sm_arts.jibcon.data.repository.helper.MobiusNetworkHelper;
import com.sm_arts.jibcon.login.KaKaoSDKAdpater;
import com.tsengvn.typekit.Typekit;

import java.lang.ref.WeakReference;


/**
 * Created by admin on 2017-04-06.
 */

public class GlobalApplication extends MultiDexApplication {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private static volatile GlobalApplication sObj = null;
    private volatile WeakReference<Activity> sCurrentActivity = null;
    //카톡 로그인

    private static OAuthLogin mOAuthLoginModule = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: allocate GlobalApplication sObj");
        sObj = this;
        KakaoSDK.init(new KaKaoSDKAdpater());
        // for font change
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "12롯데마트드림Medium.ttf"))
                .addBold(Typekit.createFromAsset(this, "12롯데마트드림Bold.ttf"))
                .addCustom1(Typekit.createFromAsset(this, "12롯데마트드림Light.ttf")); // 이후 추가시 .addCustom2~9 까지 가능

        initMobius();
    }

    private void initMobius() {
        Log.d(TAG, "initMobius: ");
        MobiusNetworkHelper.getInstance().createAe();
        MobiusNetworkHelper.getInstance().retrieveAe();
    }

    public static OAuthLogin getNaverOAuthLogin() {
        if (mOAuthLoginModule == null) {
            mOAuthLoginModule = OAuthLogin.getInstance();
            mOAuthLoginModule.init(getGlobalApplicationContext(),
                    "f2VZgOgRx7HzZaCUzN_D" , "uldOlbBiia" , "JIBCON");
        }

        return mOAuthLoginModule;
    }

    //카톡 로그인용
    public static GlobalApplication getGlobalApplicationContext() {
        return sObj;
    }

    public Activity getCurrentActivity() {
        return sCurrentActivity.get();
    }

    public boolean chkPermission(String permission, Activity currentActivity) {
        Log.d(TAG, "chkPermission: ");
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "chkPermission: Granted");
        } else {
            Log.d(TAG, "chkPermission: Getting Permission");
            String[] permissions = {permission};
            ActivityCompat.requestPermissions(currentActivity, permissions, 1);

            permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "chkPermission: Success to get permission");
            }
            else {
                Log.d(TAG, "chkPermission: Fail to get permission");
                return false;
            }
        }

        return true;
    }
}
