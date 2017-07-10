package com.sm_arts.jibcon.app.setting.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.setting.SettingActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class Alarm extends AppCompatActivity {

    @OnClick(R.id.imageview_setting_alarm) void imageview_setting_alarm(){
        Intent intent = new Intent(Alarm.this, SettingActivity.class);
        startActivity(intent);
        finish();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_alarm);
        ButterKnife.bind(this);

    }
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
