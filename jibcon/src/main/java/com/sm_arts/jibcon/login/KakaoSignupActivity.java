package com.sm_arts.jibcon.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.login.user.service.UserService;
import com.sm_arts.jibcon.login.user.service.UserServiceImpl;
import com.sm_arts.jibcon.main.MainActivity;
import com.sm_arts.jibcon.utils.SharedPreferenceHelper;

/**
 * Created by admin on 2017-01-20.
 */

public class KakaoSignupActivity extends BaseActivity {
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    GlobalApplication mApp;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("testing","KaKaoSignUpActivity_onCreate()");
        mApp = (GlobalApplication) getApplicationContext();
        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {

                Log.d("testing","KaKaoSignUpActivity_onFailure()");
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("testing","KakaoSignupActivity_onSessionClosed");
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                Log.d("testing","KaKaoSignUpActivity_onNotSignedUp()");

            } // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Log.d("testing","KaKaoSignUpActivity_onSuccess()");
                Log.d("testing","KaKaoSignUpActivity_onSuccess()"+userProfile);

                redirectMainActivity(); // 로그인 성공시 MainActivity로
            }
        });
    }

    private void redirectMainActivity() {
        //성공시 Main
        Log.d("testing","KaKaoSignUpActivit_redirectMainActivity()");

        // TODO: 2017-05-24 facebook과 유사하게 토큰 처리
        // TODO: 2017-05-24 현재 sample 계정 로그인  
        UserServiceImpl.getInstance().getSampleUserAsynchronisely(new UserService.onSuccessListener() {
            @Override
            public void onSuccessGetSampleUserAsynchronisely(User sampleUser) {
                mApp.setUser(sampleUser);
                DeviceServiceImpl.getInstance().prepareDeviceItems();
            }
        });

        SharedPreferenceHelper.saveSharedPreference("pref", "LOGINTYPE", "KAKAO");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    protected void redirectLoginActivity() {
        Log.d("testing","KaKaoSignUpActivity_redirectLoginActivity()");

        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
