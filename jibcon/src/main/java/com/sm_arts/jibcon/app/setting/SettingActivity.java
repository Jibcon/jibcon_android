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
import com.sm_arts.jibcon.utils.consts.Strings;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity {

    @BindView(R.id.lv_setting) ListView mSettingLv;
    @OnClick(R.id.imageview_setting) void imageview_setting(){
        finish();}

    private Strings strings = Strings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);

        String[] sSettingList = {
                strings.setting_menu_1,
                strings.setting_menu_2,
                strings.setting_menu_3
        };

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSettingList);

        mSettingLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/

        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingLv.getItemAtPosition(position);

                if(strings.setting_menu_1.equals(settingClickedItem)){
                    Intent intent = new Intent(SettingActivity.this, PersonSecureActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(strings.setting_menu_2.equals(settingClickedItem)){
                    Intent intent = new Intent(SettingActivity.this, AlarmActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SettingActivity.this, UserCenterActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }) ;

    }
}
