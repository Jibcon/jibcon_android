package com.sm_arts.jibcon.ui.additional.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;


import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class JibconAskActivity extends BaseActivity {
    @OnClick(R.id.imageview_setting_jibconask) void imageview_setting_jibconask() {
        Intent intent = new Intent(JibconAskActivity.this, UserCenterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_jibconaskactivity_activity);
        ButterKnife.bind(this);
    }
}
