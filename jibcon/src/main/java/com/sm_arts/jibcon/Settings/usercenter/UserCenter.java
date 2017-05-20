package com.sm_arts.jibcon.Settings.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.Settings.SettingActivity;
import com.sm_arts.jibcon.Settings.alarm.Alarm;
import com.sm_arts.jibcon.Settings.person_secure.Person_Secure;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class UserCenter extends AppCompatActivity {

    ListView mSettingUserCenterLv;
    static final String[] mSettingUserCenterList={"제조업체 문의","FAQ","집콘 문의하기"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_usercenter);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSettingUserCenterList);

        mSettingUserCenterLv = (ListView)findViewById(R.id.Lv_setting_usercenter);

        mSettingUserCenterLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSettingUserCenterLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingUserCenterLv.getItemAtPosition(position);

                if(settingClickedItem == "제조업체 문의"){
                    Intent intent = new Intent(UserCenter.this, AskEnrollCompany.class);
                    startActivity(intent);
                }
                else if(settingClickedItem=="FAQ"){
                    Intent intent = new Intent(UserCenter.this, FAQ.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(UserCenter.this, JibconAsk.class);
                    startActivity(intent);
                }
            }
        }) ;
    }
}