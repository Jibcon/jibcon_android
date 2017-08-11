package com.sm_arts.jibcon.ui.additional.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.additional.setting.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class UserCenterActivity extends BaseActivity {
    interface UsercetnerMenu {
        int ASKCOMPANY = 0;
        int FAQ = 1;
    }
    @BindView(R.id.lv_setting_usercenter) ListView mSettingUserCenterLv;
    @OnClick(R.id.imageview_setting_usercenter) void imageview_setting_usercenter() {
        Intent intent = new Intent(UserCenterActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_usercenteractivity_activity);
        ButterKnife.bind(this);

        final String[] usercenterOptionList = getResources().getStringArray(R.array.setting_usercenter_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, usercenterOptionList);
        mSettingUserCenterLv.setAdapter(adapter);

        mSettingUserCenterLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                if (position == UsercetnerMenu.ASKCOMPANY) {
                    Intent intent = new Intent(UserCenterActivity.this, AskEnrollCompanyActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (position == UsercetnerMenu.FAQ) {
                    Intent intent = new Intent(UserCenterActivity.this, FaqActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(UserCenterActivity.this, JibconAskActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}