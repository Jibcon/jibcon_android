package com.sm_arts.jibcon.app.splash;

// 매니페스트 파일에서 메인 엑티비티에 걸려있는? <intent-filter>를 IntroActivity에 넣어줌.

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.login.LoginActivity;
import com.sm_arts.jibcon.login.loginmanager.JibconLoginManagerImpl;
import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.login.user.domain.UserInfo;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class IntroActivity extends BaseActivity {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    private Handler mHandler;
    GlobalApplication mApplication;
    private CallbackManager mCallbackManager= null;

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            Log.d(TAG, "run: ");
            mCallbackManager= CallbackManager.Factory.create();
            final AccessToken accesstoken = AccessToken.getCurrentAccessToken();

            if(accesstoken!=null&&accesstoken.isExpired())
            {//accesstoken만료기간 60일
                //만료되면 로그인창으로
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }

            //기존에 로그인된 상태 체크하기
            if(accesstoken!=null) {
                //intro->login success->main
                accesstoken.getToken();

                ApiService service = RetrofiClients.getInstance().getService(ApiService.class);
                UserInfo userInfo = new UserInfo("facebook", accesstoken.getToken());
                Call<User> c = service.logincheck(userInfo);

                try {
                    c.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            JibconLoginManagerImpl.getInstance().setUser(response.body());
                            Log.d(TAG, "onResponse: "+"Success");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            // prepare deviceItems
                            DeviceServiceImpl.getInstance().prepareDeviceItems();
                            startActivity(intent);

                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {//intro->login success->tutorial
                Intent intent = new Intent(getApplicationContext(),TutorialMainActivity.class);
                startActivity(intent);
                finish();
            }

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_introactivity_activity);
        init();

        //액션바 없애기
        hideActionBar();
        itemSetup();
//        getKeyHash(getApplicationContext());

        checkUserLogin();

    }
    private  void checkUserLogin()
    {
        String currentToken;
        //getShared
        //이미 로그인된 유저먼 MainActivity로 redirect!
        //받아야 할 정보
        //유저 정보 (집콘 관련)
        //유저 디바이스 정보들
        //받은 상태에서 MainActivity로 이동하기

        mHandler.postDelayed(mRunnable, 1500);
        //intro->login failed->tutorial

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("LoginTest","onActivityResult");

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //액션바 없애기
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }
    }

    public void init() {
        mHandler = new Handler();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mRunnable);
    }


    public static String getKeyHash(Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;
        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey= android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
                //카카오톡 안드로이드 플랫폼 키해시에 들어갈 키 정보 알아내기
                Log.d("testing",hashKey);

                return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.d("testing", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    private  void itemSetup() {
        //Global Application 에 담을 정보 초기 setup;
        mApplication = (GlobalApplication)getApplicationContext();
    }

}
