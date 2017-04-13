package com.example.admin.jipcon2.Splash;

// 매니페스트 파일에서 메인 엑티비티에 걸려있는? <intent-filter>를 IntroActivity에 넣어줌.

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.jipcon2.Device.DeviceItem;
import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.R;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {
    private Handler handler;
    GlobalApplication application;
    ArrayList<DeviceItem> deviceItemArrayList;

    Runnable runnable = new Runnable(){
        @Override
        public void run(){

            //이미 로그인 되있다면 MainActivity로 redirect!!

            Intent intent = new Intent(IntroActivity.this, TutorialActivity.class);

            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };
    private  void itemSetup()
    {
     //Global Application 에 담을 정보 초기 setup;
        application = (GlobalApplication)getApplicationContext();
        deviceItemArrayList = new ArrayList<>();
        ArrayList<Bitmap> arr= new ArrayList<>();
        ArrayList<String> strarr= new ArrayList<>();
        DeviceItem item;

        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.airconditioner);
        Bitmap bitmap1 = drawable1.getBitmap();
        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.lightbulb);
        Bitmap bitmap2 = drawable2.getBitmap();
        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
        Bitmap bitmap3 = drawable3.getBitmap();
        BitmapDrawable drawable4 = (BitmapDrawable) getResources().getDrawable(R.drawable.refrigerator);
        Bitmap bitmap4 = drawable4.getBitmap();

        arr.add(0, bitmap1);
        arr.add(1,bitmap2);
        arr.add(2,bitmap3);
        arr.add(3,bitmap4);

        strarr.add(0,"airconditioner");
        strarr.add(1,"lightbulb");
        strarr.add(2,"fan");
        strarr.add(3,"refrigerator");

        for(int i=0;i<4;i++)
        {
            deviceItemArrayList.add(i, new DeviceItem(arr.get(i),strarr.get(i)));
            //초기 테스트 아이템 설정
            //여기 디바이스 메뉴 아이템
        }


        application.setDeviceItemArrayList(deviceItemArrayList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();

        //액션바 없애기
        hideActionBar();
        itemSetup();
        handler.postDelayed(runnable, 1500);

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
}
