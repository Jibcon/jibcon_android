package com.sm_arts.jibcon.ui.splash.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconStartActivity;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.utils.helper.SharedPreferenceHelper;


public class LoginActivity extends BaseActivity {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    private OAuthLoginButton mOAuthLoginButton;
    private SessionCallback mKakaoCallback;      //콜백 선언
    private OAuthLoginHandler mOAuthLoginHandler;
    private VideoView mVideoView;

    private CallbackManager mCallbackManager = null;
    private AccessTokenTracker mAccessTokenTracker = null;
    private FacebookCallback<LoginResult> mFacebookCallback = null;


    private void facebookLoginSetup() {
        FacebookSdk.sdkInitialize(getApplicationContext());

        mFacebookCallback = JibconLoginManager.getInstance()
                .makeFacebookLoginManager(
                        // TODO: 2017-10-14 집콘만들기 생략
                        this::gotoMainActivity
                );
        mCallbackManager = JibconLoginManager.getInstance()
                .makeFacebookCallbackManager();
        mAccessTokenTracker = JibconLoginManager.getInstance()
                .makeFacebookAccessTokenTracker();


        LoginButton loginButton = (LoginButton) findViewById(R.id.btn_login_facebook);
        loginButton.setReadPermissions("public_profile", "user_friends");
        loginButton.setReadPermissions("email");

        loginButton.registerCallback(mCallbackManager, mFacebookCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashlogin_login_activity);


        mVideoView = (VideoView) findViewById(R.id.videoView);

        mVideoView.setOnCompletionListener(
                (mp) -> mVideoView.start()
        );

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.login_video;
        mVideoView.setVideoURI(Uri.parse(videoPath));
        mVideoView.start();

        mOAuthLoginHandler = JibconLoginManager.getInstance()
                .getNaverOAuthLoginHandler(LoginActivity.this);
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.btn_naver_login);

        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        mOAuthLoginButton.setOnClickListener(
                v -> {
                    SharedPreferenceHelper.saveSharedPreference("pref","LOGINTYPE","NAVER");
                    GlobalApplication.getNaverOAuthLogin().startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
                }
        );

        facebookLoginSetup();
        initSampleSignInBtn();
        kakaoSetup();
    }

    private void kakaoSetup() {
        if (!Session.getCurrentSession().isClosed()) {
            redirectSignupActivity();
        }

        mKakaoCallback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(mKakaoCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

    }


    private void initSampleSignInBtn() {
        Log.d(TAG, "initSampleSignInBtn: ");
        findViewById(R.id.btnSampleSignIn).setOnClickListener(
                v -> {
                    Log.d(TAG, "initSampleSignInBtn: btnSampleSignIn clicked");
                    JibconLoginManager.getInstance().loginWithSampleUser(
//                            this::gotoMakeConStartActivity
                            //// TODO: 2017-08-07 교체하기 
                            this::gotoMainActivity
                    );
                }
        );
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();    }

    private void gotoMakeConStartActivity() {
        Intent intent = new Intent(getApplicationContext(), MakeconStartActivity.class);
        startActivity(intent);
        Log.d(TAG, "gotoMakeConStartActivity: finish");
        finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAccessTokenTracker.stopTracking();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //kakao login
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            Log.d(TAG,"LoginActivity_onSessionOpened()");

            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d(TAG,"LoginActivity_onSessionOpenFailed"+exception);
            exception.printStackTrace();
            Log.d(TAG,"LoginActivity_onSessionOpenFailed()");
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.splashlogin_login_activity); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        Log.d("testing","LoginActivity_redirectSignupActivity()");

        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    //kakao
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mKakaoCallback);
    }
}
