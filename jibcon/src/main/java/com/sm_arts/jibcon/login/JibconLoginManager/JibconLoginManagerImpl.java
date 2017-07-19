package com.sm_arts.jibcon.login.JibconLoginManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.app.makecon.MakeconStartActivity;
import com.sm_arts.jibcon.app.splash.IntroActivity;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.login.user.domain.User;
import com.sm_arts.jibcon.login.user.domain.UserInfo;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.utils.network.RetrofitUtils;

import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 2017-07-19.
 */

public class JibconLoginManagerImpl implements JibconLoginManager{

    private CallbackManager mCallbackManager = null;
    private AccessTokenTracker mAccessTokenTracker = null;
    private static final String TAG =  "JibconLoginManagerImpl";
    GlobalApplication mApp;

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
                //Toast.makeText(getApplicationContext(),"current token : "+currentAccessToken,Toast.LENGTH_SHORT).show();
            }
        };

    }

    @Override
    public FacebookCallback<LoginResult> makeFacebookLoginManager() {

        mApp=GlobalApplication.getGlobalApplicationContext();
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
                            try {
                                mApp.setUserProfileImage(new URL("https://graph.facebook.com/"+userid+"/picture"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            userEmail = object.optString("email");
                            name = object.optString("name");
                            Log.d(TAG, "onCompleted: " + userEmail + "/" + name);
                            Log.d("profilecheck","useremail :" + userEmail);
                            Log.d("profilecheck","name : " + name);

                            mApp.setUsername(object.optString("name"));

                            SharedPreferences pref = mApp.getSharedPreferences("pref",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            editor.putString("LOGINTYPE","FACEBOOK");
                            editor.commit();

                        }
                    }
                }).executeAsync();


                final String userTokenFacebook;
                userTokenFacebook=loginResult.getAccessToken().getToken();
                UserInfo userInfo=new UserInfo("facebook",userTokenFacebook);
                Log.d("MYTOKEN",userTokenFacebook);
                ApiService apiService = (ApiService) RetrofitUtils.getInstance().getService(ApiService.class);;

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
                            Intent intent= new Intent(mApp, MakeconStartActivity.class);

                            // prepare deviceItems
                            DeviceServiceImpl.getInstance().prepareDeviceItems();

                            mApp.startActivity(intent);


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

        return mFacebookCallback;
    }



    @Override
    public void logout(Context context) {

        // TODO: 2017-07-19 sharedpreference로 어떤 로그인 타입이었는지 체크
        // TODO: 2017-07-19 DB로 변경

        SharedPreferences pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE);
        String logintype;
        logintype=pref.getString("LOGINTYPE","");
        Log.d(TAG, "logout: LoginType : "+logintype);
        if(logintype.equals(""))
        {
            Log.d(TAG, "logout: logintype null");
            return;
        }
        else if(logintype.equals("FACEBOOK"))
        {
            Log.d(TAG, "logout: facebook logout");
            LoginManager.getInstance().logOut();
        }
        else if(logintype.equals("KAKAO"))
        {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {

                }
            });
        }
        else if(logintype.equals("NAVER"))
        {
            // TODO: 2017-07-19 naver logout
        }


        Intent intent = new Intent(context.getApplicationContext(), IntroActivity.class);
        context.startActivity(intent);

        ((Activity)context).finish();


    }
}
