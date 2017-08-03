package com.sm_arts.jibcon.login;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.app.makecon.MakeconStartActivity;
import com.sm_arts.jibcon.login.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.SharedPreferenceHelper;


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
                                            this::gotoMakeConStartActivity
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
        setContentView(R.layout.login_loginactivity_activity);


        mVideoView = (VideoView) findViewById(R.id.videoView);

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();
            }
        });

        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.login_video;
        mVideoView.setVideoURI(Uri.parse(videoPath));
        mVideoView.start();

        mOAuthLoginHandler = JibconLoginManager.getInstance()
                                        .getNaverOAuthLoginHandler(LoginActivity.this);
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.btn_naver_login);

        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        mOAuthLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceHelper.saveSharedPreference("pref","LOGINTYPE","NAVER");
                GlobalApplication.getNaverOAuthLogin().startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
            }
        });


        facebookLoginSetup();
        initSampleSignInBtn();
        kakaoSetup();
    }

    void kakaoSetup() {
        if (!Session.getCurrentSession().isClosed()) {
            redirectSignupActivity();
        }

        mKakaoCallback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(mKakaoCallback);

    }


    void initSampleSignInBtn() {
        Log.d(TAG, "initSampleSignInBtn: ");
        findViewById(R.id.btnSampleSignIn).setOnClickListener(
                v -> {
                    Log.d(TAG, "initSampleSignInBtn: btnSampleSignIn clicked");
                    JibconLoginManager.getInstance().loginWithSampleUser(
                            this::gotoMakeConStartActivity
                    );
                }
        );
    }

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
            Log.d("testing","LoginActivity_onSessionOpened()");

            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("testing","LoginActivity_onSessionOpenFailed"+exception);
            exception.printStackTrace();
            Log.d("testing","LoginActivity_onSessionOpenFailed()");
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.login_loginactivity_activity); // 세션 연결이 실패했을때
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
