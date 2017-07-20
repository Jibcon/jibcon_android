package com.sm_arts.jibcon.login.loginmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.app.makecon.MakeconStartActivity;
import com.sm_arts.jibcon.app.splash.IntroActivity;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.login.user.domain.UserInfo;
import com.sm_arts.jibcon.login.user.service.UserService;
import com.sm_arts.jibcon.login.user.service.UserServiceImpl;
import com.sm_arts.jibcon.login.user.service.network.UserNetworkImpl;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.utils.SharedPreferenceHelper;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import org.json.JSONObject;

import java.net.URL;

import io.reactivex.functions.Action;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-07-19.
 */

public class JibconLoginManagerImpl implements JibconLoginManager {
    private CallbackManager mCallbackManager = null;
    private AccessTokenTracker mAccessTokenTracker = null;
    private static final String TAG =  "JibconLoginManagerImpl";

    private User mUser;

    private static JibconLoginManager mInstance = null;

    public static JibconLoginManager getInstance() {
        if (mInstance == null) {
            mInstance = new JibconLoginManagerImpl();
        }

        return mInstance;
    }

    public User getCurrentUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    @Override
    public String getUserToken() {
        if (mUser != null) {
            return mUser.getEmail();
        } else {
            return null;
        }
    }

    @Override
    public String getUserProfileImageUrl() {
        if (mUser != null) {
            return mUser.getUserinfo().getPic_url();
        } else {
            return null;
        }
    }

    @Override
    public String getUserName() {
        if (mUser != null) {
            return mUser.getLast_name() + mUser.getFirst_name();
        } else {
            return null;
        }
    }

    public String getUserEmail() {
        if (mUser != null) {
            return mUser.getEmail();
        } else {
            return null;
        }
    }

    @Override
    public CallbackManager makeFacebookCallbackManager() {
        return CallbackManager.Factory.create();
    }

    @Override
    public AccessTokenTracker makeFacebookAccessTokenTracker() {
        return new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
// App code
                //DEBUG코드
                if (currentAccessToken != null) {
                    Log.d("Token", currentAccessToken.toString());
                }
                Log.d(TAG, "onCurrentAccessTokenChanged: " + currentAccessToken);
            }
        };

    }

    //naver login handler
    public OAuthLoginHandler getNaverOAuthLoginHandler(final Context context) {
        return new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {

                if (success) {//네이버 로그인 성공시
                    OAuthLogin mOAuthLogin = GlobalApplication.getNaverOAuthLogin();
                    Context mContext = GlobalApplication.getGlobalApplicationContext();
                    String accessToken = mOAuthLogin.getAccessToken(mContext);
                    String refreshToken = mOAuthLogin.getRefreshToken(mContext);
                    long expiresAt = mOAuthLogin.getExpiresAt(mContext);
                    String tokenType = mOAuthLogin.getTokenType(mContext);

                    Log.d(TAG, "run: accesstoken : " + accessToken);
                    Log.d(TAG, "run: refreshtoken : " + refreshToken);
                    Log.d(TAG, "run: expiresAt : " + expiresAt);
                    Log.d(TAG, "run: tokenType : " + tokenType);


                    //일단 샘플계정 로그인으로
                    JibconLoginManagerImpl.getInstance().loginWithSampleUser(
                            () -> context.startActivity(new Intent(context,MakeconStartActivity.class))
                    );
                } else {

                }
            }
        };
    }


    @Override
    public FacebookCallback<LoginResult> makeFacebookLoginManager() {

        FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
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
                            userEmail = object.optString("email");
                            name = object.optString("name");
                            Log.d(TAG, "onCompleted: " + userEmail + "/" + name);
                            Log.d("profilecheck","useremail :" + userEmail);
                            Log.d("profilecheck","name : " + name);

                            SharedPreferenceHelper.saveSharedPreference("pref","LOGINTYPE","FACEBOOK");
                        }
                    }
                }).executeAsync();


                final String userTokenFacebook;
                userTokenFacebook = loginResult.getAccessToken().getToken();
                UserInfo userInfo = new UserInfo("facebook",userTokenFacebook);
                Log.d("MYTOKEN", userTokenFacebook);
                ApiService apiService = RetrofiClients.getInstance()
                        .getService(ApiService.class);;

                Call<User> c = apiService.login(userInfo);
                try {
                    c.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            JibconLoginManagerImpl.getInstance()
                                    .setUser(response.body());
                            Log.d(TAG, "onResponse: "+"success");
                            Intent intent = new Intent(GlobalApplication.getGlobalApplicationContext(),
                                    MakeconStartActivity.class);

                            // prepare deviceItems
                            DeviceServiceImpl.getInstance().prepareDeviceItems();

                            GlobalApplication.getGlobalApplicationContext().startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            t.printStackTrace();
                            Log.d(TAG, "onFailure: "+"fail");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: "+"cancled");
            }

            @Override
            public void onError(FacebookException e) {

            }
        };

        return mFacebookCallback;
    }



    @Override
    public void logout(Context context) {

        // TODO: 2017-07-19 sharedpreference로 어떤 로그인 타입이었는지 체크
        // TODO: 2017-07-19 DB로 변경

        String logintype;
        logintype = SharedPreferenceHelper.getSharedPrefrence("pref","LOGINTYPE");

        Log.d(TAG, "logout: LoginType : "+logintype);
        if(logintype.equals("")) {
            Log.d(TAG, "logout: logintype null");
            return;
        }
        else if(logintype.equals("FACEBOOK")) {
            Log.d(TAG, "logout: facebook logout");
            LoginManager.getInstance().logOut();
        }
        else if(logintype.equals("KAKAO")) {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {

                }
            });
        }
        else if(logintype.equals("NAVER")) {
            OAuthLogin.getInstance().logout(context);
            // TODO: 2017-07-19 naver logout
        }

        Intent intent = new Intent(context.getApplicationContext(), IntroActivity.class);
        context.startActivity(intent);

        ((Activity) context).finish();

        mUser = null;
    }

    @Override
    public void loginWithSampleUser(Action action) {
        if (!userSignin()) {
            UserNetworkImpl.getInstance()
                    .getSampleUserInfoFromServerAsynchronisely(
                            (user) -> {
                                setUser(user);
                                Log.d(TAG, "getSampleUser: user=" + user.toString());

                                try {
                                    action.run();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } else {
            Log.e(TAG, "loginWithSampleUser: already user signed in");
        }
    }

    public boolean userSignin() {
        return (mUser != null);
    }
}