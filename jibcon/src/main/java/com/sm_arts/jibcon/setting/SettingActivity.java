package com.sm_arts.jibcon.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.setting.alarm.Alarm;
import com.sm_arts.jibcon.setting.personsecure.PersonSecure;
import com.sm_arts.jibcon.setting.usercenter.UserCenter;
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
                    Intent intent = new Intent(SettingActivity.this, PersonSecure.class);
                    startActivity(intent);
                    finish();
                }
                else if(settingClickedItem=="알림"){
                    Intent intent = new Intent(SettingActivity.this, Alarm.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SettingActivity.this, UserCenter.class);
                    startActivity(intent);
                    finish();
                }
            }
        }) ;
        ImageView mImageView = (ImageView)findViewById(R.id.imageview_setting);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**/
    }



    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
