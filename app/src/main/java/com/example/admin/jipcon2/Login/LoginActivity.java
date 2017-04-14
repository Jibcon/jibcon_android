package com.example.admin.jipcon2.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.MakeCon.MakeConStartActivity;
import com.example.admin.jipcon2.R;
import com.example.admin.jipcon2.network.ApiService;
import com.example.admin.jipcon2.network.repo;
import com.example.admin.jipcon2.network.userinfo.User;
import com.example.admin.jipcon2.network.userinfo.UserInfo;
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

import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {



//--블로그
private CallbackManager callbackManager = null;
    private AccessTokenTracker accessTokenTracker = null;
    private com.facebook.login.widget.LoginButton loginButton = null;
    GlobalApplication app;

    //facebook
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        String userEmail;
        String name;

        @Override
        public void onSuccess(LoginResult loginResult) {


            //유저 정보 받아오기
            GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback(){
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    if(response.getError()!=null)
                    {
                        Log.d("profilecheck","onCompleted() error");
                        //error handle

                    }
                    else
                    {
                        String userid;

                        userid=object.optString("id");
                        try{
                            app.setUserProfileImage(new URL("https://graph.facebook.com/"+userid+"/picture"));

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        userEmail= object.optString("email");
                        name=object.optString("name");
                        Toast.makeText(getApplicationContext(),userEmail+"/"+name,Toast.LENGTH_SHORT).show();
                        Log.d("profilecheck","useremail :"+userEmail);
                        Log.d("profilecheck","name : "+name);

                        app.setUsername(object.optString("name"));

                        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("name",object.optString("name"));
                        Log.d("profieImage",app.getUserProfileImage().toString());
                        editor.putString("profileImage",app.getUserProfileImage().toString());
                        editor.commit();
                    }
                }
            }).executeAsync();


            //Log.d("profilecheck","getname() :"+profile.getName());
            //Log.d("profilecheck","getfirstname() :"+profile.getFirstName());
            //Log.d("profilecheck","getLinkUri() :"+profile.getLinkUri());
            //Log.d("profilecheck","getId() :"+profile.getId());
            //Log.d("profilecheck","getLastName() :"+profile.getLastName());
            //서버로 전송


//            Toast.makeText(getApplicationContext(),profile.getName()+" 님 환영합니다",Toast.LENGTH_SHORT).show();
            final String userTokenFacebook;
            userTokenFacebook=loginResult.getAccessToken().getUserId();
            UserInfo userInfo=new UserInfo("facebook",userTokenFacebook);

            ApiService apiService = new repo().getService();
            Call<User> c = apiService.login(userInfo);
            try
            {
                c.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        //debug코드
                        Toast.makeText(getApplicationContext(), response.body().getEmail()+" 들어옴", Toast.LENGTH_LONG).show();


// Toast.makeText(getActivity().getApplicationContext(), loginResult.getAccessToken().getToken(), Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(), MakeConStartActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "User sign in canceled!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app=(GlobalApplication)getApplicationContext();

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
// App code
                //DEBUG코드
                if(currentAccessToken!=null)
                     Log.d("Token",currentAccessToken.toString());
                Toast.makeText(getApplicationContext(),"current token : "+currentAccessToken,Toast.LENGTH_SHORT).show();
                 }
        };

        LoginButton loginButton = (LoginButton)findViewById(R.id.Btn_Login_Facebook);
        loginButton.setReadPermissions("public_profile", "user_friends");
        loginButton.setReadPermissions("email");


        //loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);


    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }




}
