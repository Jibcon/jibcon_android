package com.sm_arts.jibcon.app.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.setting.SettingActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class UserCenterActivity extends BaseActivity {

    @BindView(R.id.lv_setting_usercenter) ListView mSettingUserCenterLv;
    @BindString(R.string.setting_usercenter_menu_1) String menu1;
    @BindString(R.string.setting_usercenter_menu_2) String menu2;
    @BindString(R.string.setting_usercenter_menu_3) String menu3;

    @OnClick(R.id.imageview_setting_usercenter) void imageview_setting_usercenter(){
        Intent intent = new Intent(UserCenterActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_usercenter_activity);
        ButterKnife.bind(this);

        String[] SettingUserCenterList = {menu1,menu2,menu3};
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, SettingUserCenterList);

        mSettingUserCenterLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSettingUserCenterLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingUserCenterLv.getItemAtPosition(position);

                if(settingClickedItem == menu1){
                    Intent intent = new Intent(UserCenterActivity.this, AskEnrollCompanyActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(settingClickedItem==menu2){
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
        }) ;

    }
}