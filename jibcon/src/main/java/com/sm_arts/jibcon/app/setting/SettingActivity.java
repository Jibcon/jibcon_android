package com.sm_arts.jibcon.app.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.setting.alarm.AlarmActivity;
import com.sm_arts.jibcon.app.setting.personsecure.PersonSecureActivity;

import com.sm_arts.jibcon.app.setting.usercenter.UserCenterActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity {
    interface OptionMenus {
        int SECURITY = 0;
        int NOTICE = 1;
    }

    @BindView(R.id.lv_setting) ListView mSettingLv;
    @OnClick(R.id.imageview_setting) void imageview_setting() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_settingactivity_activity);
        ButterKnife.bind(this);

        final String[] settingOptionmenuList =getResources().getStringArray(R.array.setting_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, settingOptionmenuList);
        mSettingLv.setAdapter(adapter);

        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent;

                if (position == OptionMenus.SECURITY) {
                    intent = new Intent(SettingActivity.this, PersonSecureActivity.class);
                }
                else if (position == OptionMenus.NOTICE) {
                    intent = new Intent(SettingActivity.this, AlarmActivity.class);
                }
                else{
                    intent = new Intent(SettingActivity.this, UserCenterActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
    }
}
