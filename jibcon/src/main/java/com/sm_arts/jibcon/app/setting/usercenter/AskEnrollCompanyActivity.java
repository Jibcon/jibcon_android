package com.sm_arts.jibcon.app.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sm_arts.jibcon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class AskEnrollCompanyActivity extends AppCompatActivity {
    @OnClick(R.id.imageview_setting_askenrollcompany) void imageview_setting_askenrollcompany(){
            Intent intent = new Intent(AskEnrollCompanyActivity.this, UserCenterActivity.class);
            startActivity(intent);
            finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_askenrollcompany);
        ButterKnife.bind(this);
    }

}
