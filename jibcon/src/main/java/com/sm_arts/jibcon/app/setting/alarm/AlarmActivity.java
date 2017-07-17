package com.sm_arts.jibcon.app.setting.alarm;

import android.content.Intent;
import android.os.Bundle;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.setting.SettingActivity;


import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class AlarmActivity extends BaseActivity {


    @OnClick(R.id.imageview_setting_alarm) void imageview_setting_alarm(){
        Intent intent = new Intent(AlarmActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_alarmactivity_activity);
        ButterKnife.bind(this);

    }

}
