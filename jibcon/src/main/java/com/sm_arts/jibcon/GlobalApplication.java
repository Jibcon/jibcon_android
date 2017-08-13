package com.sm_arts.jibcon;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.kakao.auth.KakaoSDK;
import com.nhn.android.naverlogin.OAuthLogin;
import com.sm_arts.jibcon.data.repository.helper.MobiusNetworkHelper;
import com.sm_arts.jibcon.ui.splash.login.KaKaoSDKAdpater;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.mqtt.MqttManager;
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

        initTypekit();
        initMobius();
        JibconLoginManager.getInstance().addOnSigninAction(
                this::initMqttManager
        );

        // TODO: 8/11/17 remove
        JibconLoginManager.getInstance().loginWithSampleUser(
                () -> {
                }
        );
    }

    private void initTypekit() {
        Log.d(TAG, "initTypekit: ");
        Typekit.getInstance()
                .addBold(Typekit.createFromAsset(this, "fonts/NanumSquareExtraBold.ttf"))
                .addNormal(Typekit.createFromAsset(this, "fonts/NanumSquareRegular.ttf"))
                .addCustom1(Typekit.createFromAsset(this, "fonts/NanumSquareLight.ttf"));
    }

    private void initMqttManager() {
        Log.d(TAG, "initMqttManager: ");
        MqttManager.init();
    }

    private void initMobius() {
        Log.d(TAG, "initMobius: ");
        MobiusNetworkHelper.getInstance().createAe(
                createAe -> {
                    MobiusNetworkHelper.getInstance().retrieveAe(
                            retrieveAe -> {
                            }
                    );
                }
        );


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
}
