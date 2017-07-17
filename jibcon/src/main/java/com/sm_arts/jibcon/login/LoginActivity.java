package com.sm_arts.jibcon.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.app.makecon.MakeconStartActivity;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.login.user.domain.UserInfo;
import com.sm_arts.jibcon.login.user.service.UserService;
import com.sm_arts.jibcon.login.user.service.UserServiceImpl;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.network.Repo;

import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    private SessionCallback mKakaoCallback;      //콜백 선언
    private OAuthLoginButton mOAuthLoginButton;
    private VideoView mVideoView;


    //--블로그
    private CallbackManager mCallbackManager = null;
    private AccessTokenTracker mAccessTokenTracker = null;
    GlobalApplication mApp;

    //facebook
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        String userEmail;
        String name;

        @Override
        public void onSuccess(LoginResult loginResult) {
            //유저 정보 받아오기
            GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    if(response.getError() != null) {
                        Log.d("profilecheck","onCompleted() error");
                        //error handle
                    } else {
                        String userid;

                        userid = object.optString("id");
                        try {
                            mApp.setUserProfileImage(new URL("https://graph.facebook.com/"+userid+"/picture"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        userEmail = object.optString("email");
                        name = object.optString("name");
                        Log.d(TAG, "onCompleted: " + userEmail + "/" + name);
                        //Toast.makeText(getApplicationContext(),userEmail+"/"+name,Toast.LENGTH_SHORT).show();
                        Log.d("profilecheck","useremail :" + userEmail);
                        Log.d("profilecheck","name : " + name);

                        mApp.setUsername(object.optString("name"));

                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("id", userid);
                        editor.putString("name", object.optString("name"));
                        Log.d("profieImage", mApp.getUserProfileImage().toString());
                        editor.putString("profileImage", mApp.getUserProfileImage().toString());
                        editor.commit();
                    }
                }
            }).executeAsync();


            final String userTokenFacebook;
            userTokenFacebook=loginResult.getAccessToken().getToken();
            UserInfo userInfo=new UserInfo("facebook", userTokenFacebook);
            Log.d("MYTOKEN", userTokenFacebook);
            ApiService apiService = new Repo().getService();
            Call<User> c = apiService.login(userInfo);
            try
            {
                c.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        mApp.setUserEmail(response.body().getEmail());
                        mApp.setUserToken(response.body().getToken());
                        Log.d(TAG, "onResponse: "+"success");
                        //Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), MakeconStartActivity.class);

                        // prepare deviceItems
                        DeviceServiceImpl.getInstance().prepareDeviceItems();

                        startActivity(intent);

                        finish();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                        Log.d(TAG, "onFailure: "+"fail");
                        //Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel: "+"cancled");
            //Toast.makeText(getApplicationContext(), "User sign in canceled!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(FacebookException e) {

        }
    };


    //naver login handler

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {

            if(success)
            {//네이버 로그인 성공시
                OAuthLogin mOAuthLogin = GlobalApplication.getNaverOAuthLogin();
                Context mContext = getApplicationContext();
                String accessToken = mOAuthLogin.getAccessToken(mContext);
                String refreshToken= mOAuthLogin.getRefreshToken(mContext);
                long expiresAt = mOAuthLogin.getExpiresAt(mContext);
                String tokenType = mOAuthLogin.getTokenType(mContext);

                Log.d(TAG, "run: accesstoken : "+accessToken);
                Log.d(TAG, "run: refreshtoken : "+refreshToken);
                Log.d(TAG, "run: expiresAt : "+expiresAt);
                Log.d(TAG, "run: tokenType : "+tokenType);


                //일단 샘플계정 로그인으로
                UserServiceImpl.getInstance().getSampleUserAsynchronisely(new UserService.onSuccessListener() {
                    @Override
                    public void onSuccessGetSampleUserAsynchronisely(User sampleUser) {
                        Log.d(TAG, "onSuccessGetSampleUserAsynchronisely: ");
                        mApp.setUser(sampleUser);
                        DeviceServiceImpl.getInstance().prepareDeviceItems();

                        gotoMakeConStartActivity();
                    }
                });


            }
            else
            {

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_activity);

        mVideoView = (VideoView)findViewById(R.id.videoView);

        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.btn_naver_login);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        mOAuthLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApplication.getNaverOAuthLogin().startOauthLoginActivity(LoginActivity.this,mOAuthLoginHandler);
            }
        });


        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();
            }
        });

        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.login_video;
        mVideoView.setVideoURI(Uri.parse(videoPath));
        mVideoView.start();

        mApp = (GlobalApplication) getApplicationContext();

        FacebookSdk.sdkInitialize(getApplicationContext());

        mCallbackManager = CallbackManager.Factory.create();

        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
// App code
                //DEBUG코드
                if (currentAccessToken != null) {
                    Log.d("Token", currentAccessToken.toString());
                }
                Log.d(TAG, "onCurrentAccessTokenChanged: " + currentAccessToken);
                //Toast.makeText(getApplicationContext(),"current token : "+currentAccessToken,Toast.LENGTH_SHORT).show();
                 }
        };

        LoginButton loginButton = (LoginButton) findViewById(R.id.Btn_Login_Facebook);
        loginButton.setReadPermissions("public_profile", "user_friends");
        loginButton.setReadPermissions("email");

        //loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);

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
        findViewById(R.id.btnSampleSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btnSampleSignIn");
                UserServiceImpl.getInstance().getSampleUserAsynchronisely(new UserService.onSuccessListener() {
                    @Override
                    public void onSuccessGetSampleUserAsynchronisely(User sampleUser) {
                        Log.d(TAG, "onSuccessGetSampleUserAsynchronisely: ");
                        mApp.setUser(sampleUser);
                        DeviceServiceImpl.getInstance().prepareDeviceItems();

                        gotoMakeConStartActivity();
                    }
                });
            }
        });
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
            setContentView(R.layout.login_main_activity); // 세션 연결이 실패했을때
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
