package com.sm_arts.jibcon.Settings.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.Settings.SettingActivity;
import com.sm_arts.jibcon.Settings.person_secure.Person_Secure;
import com.sm_arts.jibcon.Settings.usercenter.UserCenter;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class Alarm extends AppCompatActivity {

    ListView mSettingAlarmLv;
    static final String[] mSettingAlarmList={"푸쉬 알림","진동 알림","에너지 경고 알림","conshop 알림"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_alarm);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSettingAlarmList);

        mSettingAlarmLv = (ListView)findViewById(R.id.Lv_setting_alarm);

        mSettingAlarmLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSettingAlarmLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingAlarmLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

    }



    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
