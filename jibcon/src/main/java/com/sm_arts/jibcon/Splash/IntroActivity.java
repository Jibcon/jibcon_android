package com.sm_arts.jibcon.Splash;

// 매니페스트 파일에서 메인 엑티비티에 걸려있는? <intent-filter>를 IntroActivity에 넣어줌.

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sm_arts.jibcon.Device.DeviceItem;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.Login.LoginActivity;
import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.network.ApiService;
import com.sm_arts.jibcon.network.repo;
import com.sm_arts.jibcon.Login.user.domain.User;
import com.sm_arts.jibcon.Login.user.domain.UserInfo;
import com.sm_arts.jibcon.Device.service.DeviceServiceImpl;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class IntroActivity extends AppCompatActivity {
    private Handler handler;
    GlobalApplication application;
    ArrayList<DeviceItem> deviceItemArrayList;
    private CallbackManager callbackManager = null;

    Runnable runnable = new Runnable(){
        @Override
        public void run(){

            callbackManager = CallbackManager.Factory.create();
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

                ApiService service = new repo().getService();
                UserInfo userInfo = new UserInfo("facebook", accesstoken.getToken());
                Call<User> c = service.logincheck(userInfo);

                try
                {
                    c.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            application.setUserEmail(response.body().getEmail());
                            application.setUserToken(response.body().getToken());
                            application.setUsername(response.body().getUserinfo().getFull_name());
                            Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                            try{
                            application.setUserProfileImage(new URL(response.body().getUserinfo().getPic_url()));
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                            // prepare deviceItems
                            DeviceServiceImpl.getInstance().prepareDeviceItems();
                            startActivity(intent);

                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                //response->
                //sharedpreferece에 저장된 값으로 유저 이름, 이미지 설정



//                try
//                {
//                    application.setUserProfileImage(new URL(pref.getString("profileImage","")));
//
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }

            }
            else
            {//intro->login success->tutorial

                Intent intent = new Intent(getApplicationContext(),TutorialActivity.class);
                startActivity(intent);
                finish();
            }


            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
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

        handler.postDelayed(runnable, 1500);
        //intro->login failed->tutorial

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("LoginTest","onActivityResult");

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //액션바 없애기
    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }
    }

    public void init(){
        handler = new Handler();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }


    public static String getKeyHash(Context context)
    {
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

    private  void itemSetup()
    {

        //Global Application 에 담을 정보 초기 setup;
        application = (GlobalApplication)getApplicationContext();
//        deviceItemArrayList = new ArrayList<>();
        ArrayList<Bitmap> arr= new ArrayList<>();
        ArrayList<String> strarr= new ArrayList<>();
        DeviceItem item;
//
//        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.airconditioner);
//        Bitmap bitmap1 = drawable1.getBitmap();
//        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.lightbulb);
//        Bitmap bitmap2 = drawable2.getBitmap();
//        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
//        Bitmap bitmap3 = drawable3.getBitmap();
//        BitmapDrawable drawable4 = (BitmapDrawable) getResources().getDrawable(R.drawable.refrigerator);
//        Bitmap bitmap4 = drawable4.getBitmap();
//
//        arr.add(0, bitmap1);
//        arr.add(1,bitmap2);
//        arr.add(2,bitmap3);
//        arr.add(3,bitmap4);
//
//
//        strarr.add(0,"airconditioner");
//        strarr.add(1,"lightbulb");
//        strarr.add(2,"fan");
//        strarr.add(3,"refrigerator");
//
//        for(int i=0;i<4;i++)
//        {
//            deviceItemArrayList.add(i, new DeviceItem(i,strarr.get(i)));
//            //초기 테스트 아이템 설정
//            //여기 디바이스 메뉴 아이템
//        }


//        application.setDeviceItemArrayList(deviceItemArrayList);
    }


}
