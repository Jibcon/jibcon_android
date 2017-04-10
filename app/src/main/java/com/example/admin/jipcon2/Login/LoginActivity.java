package com.example.admin.jipcon2.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.MainActivity;
import com.example.admin.jipcon2.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginActivity extends AppCompatActivity {



//--블로그
private CallbackManager callbackManager = null;
    private AccessTokenTracker accessTokenTracker = null;
    private com.facebook.login.widget.LoginButton loginButton = null;
    GlobalApplication app;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            String userTokenFacebook;
            userTokenFacebook=loginResult.getAccessToken().getUserId();

            //debug코드
            Toast.makeText(getApplicationContext(), loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();


// Toast.makeText(getActivity().getApplicationContext(), loginResult.getAccessToken().getToken(), Toast.LENGTH_LONG).show();
            Intent intent= new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

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



        accessTokenTracker.startTracking();

        LoginButton loginButton = (LoginButton)findViewById(R.id.Btn_Login_Facebook);
        loginButton.setReadPermissions("public_profile", "user_friends");
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
