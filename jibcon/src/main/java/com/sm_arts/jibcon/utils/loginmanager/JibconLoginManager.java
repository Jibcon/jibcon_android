package com.sm_arts.jibcon.utils.loginmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.data.models.api.dto.User;
import com.sm_arts.jibcon.data.models.api.dto.UserInfo;
import com.sm_arts.jibcon.data.repository.helper.network.UserNetworkImpl;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconStartActivity;
import com.sm_arts.jibcon.ui.splash.tutorial.IntroActivity;
import com.sm_arts.jibcon.utils.helper.SharedPreferenceHelper;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-07-19.
 */

public class JibconLoginManager {
    private static final String TAG = "JibconLoginManager";
    private final String PREF_NAME = "pref";
    private final String PREF_LOGINTYPE = "LOGINTYPE";
    private final String PREF_TYPE_NAVER = "NAVER";
    private final String PREF_TYPE_FACEBOOK = "FACEBOOK";
    private final String PREF_TYPE_KAKAO = "KAKAO";
    private final String PREF_TYPE_SAMPLE = "SAMPLE";
    private static JibconLoginManager mInstance = null;

    private User mUser;
    private List<Action> mAftersigninActions = new ArrayList<>();

    public static JibconLoginManager getInstance() {
        if (mInstance == null) {
            synchronized (JibconLoginManager.class) {
                if (mInstance == null) {
                    mInstance = new JibconLoginManager();
                }
            }
        }
        return mInstance;
    }

    public User getCurrentUser() {
        return mUser;
    }

    public void setUserOnSuccess(@NonNull User mUser) {
        Log.d(TAG, "setUserOnSuccess: mUser=" + mUser);
        this.mUser = mUser;
        runAftersigninActions();
    }

    private void runAftersigninActions() {
        Log.d(TAG, "runAftersigninActions: with mAftersigninActions.size = "
                + mAftersigninActions.size());

        for (Action action :
                mAftersigninActions) {
            try {
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mAftersigninActions.clear();
    }

    public String getUserTokenAsHeader() {
        return "Token " + getUserToken();
    }

    public String getUserToken() {
        if (mUser != null) {
            return mUser.getToken();
        } else {
            return null;
        }
    }

    public String getUserProfileImageUrl() {
        if (mUser != null) {
            return mUser.getUserinfo().getPic_url();
        } else {
            return null;
        }
    }


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


    public CallbackManager makeFacebookCallbackManager() {
        return CallbackManager.Factory.create();
    }


    public AccessTokenTracker makeFacebookAccessTokenTracker() {
        return new AccessTokenTracker() {

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
                    JibconLoginManager.getInstance().loginWithSampleUser(
                            () -> context.startActivity(new Intent(context, MakeconStartActivity.class))
                    );
                } else {

                }
            }
        };
    }


    public FacebookCallback<LoginResult> makeFacebookLoginManager(Action action) {

        FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
            String userEmail;
            String name;
            @Override
            public void onSuccess(LoginResult loginResult) {
                //유저 정보 받아오기
                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (response.getError() != null) {
                            Log.d("profilecheck", "onCompleted() error");
                            //error handle
                        } else {
                            String userid;
                            userid = object.optString("id");
                            userEmail = object.optString("email");
                            name = object.optString("name");
                            Log.d(TAG, "onCompleted: " + userEmail + "/" + name);
                            Log.d("profilecheck", "useremail :" + userEmail);
                            Log.d("profilecheck", "name : " + name);
                            SharedPreferenceHelper.saveSharedPreference(PREF_NAME, PREF_LOGINTYPE, PREF_TYPE_FACEBOOK);
                        }
                    }
                }).executeAsync();


                final String userTokenFacebook;
                userTokenFacebook = loginResult.getAccessToken().getToken();
                UserInfo userInfo = new UserInfo("facebook", userTokenFacebook);
                Log.d("MYTOKEN", userTokenFacebook);
                UserService userService = RetrofitClients.getInstance()
                        .getService(UserService.class);

                Call<User> c = userService.login(userInfo);
                try {
                    c.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                JibconLoginManager.getInstance()
                                        .setUserOnSuccess(response.body());
                                Log.d(TAG, "onResponse: " + "success");
                                updateFcmToken();
                                try {
                                    action.run();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.w(TAG, "makeFacebookLoginManager/onResponse: failed to signin with usreinfo");
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            t.printStackTrace();
                            Log.d(TAG, "onFailure: " + "fail");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: " + "cancled");
            }

            @Override
            public void onError(FacebookException e) {

            }
        };
        return mFacebookCallback;
    }

    public void updateFcmToken() {


        UserService userService = RetrofitClients.getInstance()
                .getService(UserService.class);

        String fcm_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onCreate: FirebaseToken : " + fcm_token);

        Call<User> c = userService.updateFcmToken(JibconLoginManager.getInstance().getUserToken(), fcm_token);
        c.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                JibconLoginManager.getInstance().setUserOnSuccess(response.body());
                Log.d(TAG, "onResponse: "+"fcm 등록 성공");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onResponse: "+"fcm 등록 실패");
            }
        });
    }


    public void logout(Context context) {

        // TODO: 2017-07-19 sharedpreference로 어떤 로그인 타입이었는지 체크
        // TODO: 2017-07-19 DB로 변경

        String logintype;
        logintype = SharedPreferenceHelper.getSharedPrefrence(PREF_NAME, PREF_LOGINTYPE);

        //LoginManager.getInstance().logOut();
        mUser = null;

        Log.d(TAG, "logout: LoginType : " + logintype);
        if (logintype.equals("")) {
            Log.d(TAG, "logout: logintype null");
            return;
        } else if (TextUtils.equals(logintype, PREF_TYPE_SAMPLE)) {
            // TODO: 7/21/17 implement
            Log.d(TAG, "logout: SAMPLE");
        } else if (logintype.equals(PREF_TYPE_FACEBOOK)) {
            Log.d(TAG, "logout: facebook logout");
            LoginManager.getInstance().logOut();
        } else if (logintype.equals(PREF_TYPE_KAKAO)) {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {

                }
            });
        } else if (logintype.equals(PREF_TYPE_NAVER)) {
            OAuthLogin.getInstance().logout(context);
            // TODO: 2017-07-19 naver logout
        }

        Intent intent = new Intent(context.getApplicationContext(), IntroActivity.class);
        context.startActivity(intent);

        ((Activity) context).finish();
    }

    public void loginWithKakao(String accessToken, Action action) {
        UserInfo userInfo = new UserInfo("kakao", accessToken);
        UserService userService = RetrofitClients.getInstance()
                .getService(UserService.class);
        Call<User> c = userService.login(userInfo);
        c.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                JibconLoginManager.getInstance().setUserOnSuccess(response.body());
                SharedPreferenceHelper.saveSharedPreference(PREF_NAME, PREF_LOGINTYPE, PREF_TYPE_KAKAO);
                updateFcmToken();
                try {
                    action.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void loginWithSampleUser(Action action) {
        if (!userSignin()) {
            UserNetworkImpl.getInstance()
                    .getSampleUserInfoFromServerAsynchronisely(
                            (user) -> {
                                if (user != null) {
                                    setUserOnSuccess(user);
                                    Log.d(TAG, "getSampleUser: user=" + user.toString());
                                    saveSharedPrefWithSampleUser();
                                    updateFcmToken();
                                    try {
                                        action.run();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.w(TAG, "loginWithSampleUser: user is null");
                                }
                            }
                    );
        } else {
            Log.e(TAG, "loginWithSampleUser: already user signed in");
            try {
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSharedPrefWithSampleUser() {
        saveSharedPref(PREF_TYPE_SAMPLE);
    }

    private void saveSharedPref(String type) {
        SharedPreferenceHelper.saveSharedPreference(PREF_NAME, PREF_LOGINTYPE, type);
    }

    public boolean userSignin() {
        return (mUser != null);
    }

    public void addOnSigninAction() {

    }

    public void addOnSigninAction(Action aftersigninAction) {
        mAftersigninActions.add(aftersigninAction);
    }
}
