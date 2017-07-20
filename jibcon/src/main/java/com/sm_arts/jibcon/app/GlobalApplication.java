package com.sm_arts.jibcon.app;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.kakao.auth.KakaoSDK;
import com.sm_arts.jibcon.login.KaKaoSDKAdpater;
import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.login.user.service.network.UserNetworkImpl;
import com.tsengvn.typekit.Typekit;

import java.lang.ref.WeakReference;
import java.net.URL;

import io.reactivex.functions.Consumer;

import static com.kakao.util.helper.Utility.getPackageInfo;

/**
 * Created by admin on 2017-04-06.
 */

public class GlobalApplication extends MultiDexApplication {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    //모든 액티비티에서 공유할 수 있는 정보만 담기 최대 4KB..?

    private static volatile GlobalApplication sObj = null;
    private volatile WeakReference<Activity> sCurrentActivity = null;
    //카톡 로그인

    String userToken;
    //ArrayList<DeviceItem> deviceItemArrayList;//device 메뉴 아이템들의 리스트
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
        // todo remove
        if (!userSignin()) {
            User user = UserNetworkImpl.getInstance()
                    .getSampleUserInfoFromServerSynchronisely();
            userToken = user.getToken();
            Log.d(TAG, "getUserToken: " + userToken);
        }

        return userToken;
    }

    public String getUserToken(Consumer<String> comsumer) {
        // todo remove
        if (!userSignin()) {
            UserNetworkImpl.getInstance()
                    .getSampleUserInfoFromServerAsynchronisely(
                        (user) -> {
                            try {
                                String token = user.getToken();
                                setUserToken(token);
                                Log.d(TAG, "getUserToken: " + token);
                                comsumer.accept(token);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
            );
        }

        return userToken;
    }

    public boolean userSignin() {
        if (userToken == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: allocate GlobalApplication sObj");
        sObj = this;
        username = "TestUser";
        userEmail = "Jipcon@Jipcon.com";
        KakaoSDK.init(new KaKaoSDKAdpater());
        // for font change
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "12롯데마트드림Medium.ttf"))
                .addBold(Typekit.createFromAsset(this, "12롯데마트드림Bold.ttf"))
                .addCustom1(Typekit.createFromAsset(this, "12롯데마트드림Light.ttf")); // 이후 추가시 .addCustom2~9 까지 가능
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

    public void setUser(User user) {
        setUsername(user.getUserinfo().getFull_name());
        setUserEmail(user.getEmail());
        setUserToken(user.getToken());

        try {
            setUserProfileImage(new URL(user.getUserinfo().getPic_url()));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Toast.makeText(getApplicationContext(),"Success Signin With SampleUser",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "setUser: Success Signin With SampleUser");
    }

    // For Thirdparty App Key
//    public String getKeyHash(Context context)
//    {
//        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
//        if (packageInfo == null)
//            return null;
//        for (Signature signature : packageInfo.signatures) {
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String hashKey= android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
//                //카카오톡 안드로이드 플랫폼 키해시에 들어갈 키 정보 알아내기
//                Log.d("testing",hashKey);
//
//                return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
//            } catch (NoSuchAlgorithmException e) {
//                Log.d("testing", "Unable to get MessageDigest. signature=" + signature, e);
//            }
//        }
//        return null;
//    }
}
