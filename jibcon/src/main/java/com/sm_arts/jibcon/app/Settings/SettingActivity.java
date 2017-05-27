package com.sm_arts.jibcon.app.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.Settings.alarm.Alarm;
import com.sm_arts.jibcon.app.Settings.person_secure.Person_Secure;
import com.sm_arts.jibcon.app.Settings.usercenter.UserCenter;
import com.tsengvn.typekit.TypekitContextWrapper;



public class SettingActivity extends AppCompatActivity {

    ListView mSettingLv;
    static final String[] mSettingList={"개인/보안","알림","고객센터"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSettingList);

        mSettingLv = (ListView)findViewById(R.id.Lv_setting);

        mSettingLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingLv.getItemAtPosition(position);

                if(settingClickedItem == "개인/보안"){
                    Intent intent = new Intent(SettingActivity.this, Person_Secure.class);
                    startActivity(intent);
                }
                else if(settingClickedItem=="알림"){
                    Intent intent = new Intent(SettingActivity.this, Alarm.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SettingActivity.this, UserCenter.class);
                    startActivity(intent);
                }
            }
        }) ;


        /**/
    }



    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
