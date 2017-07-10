package com.sm_arts.jibcon.app.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.setting.alarm.Alarm;
import com.sm_arts.jibcon.app.setting.personsecure.PersonSecure;
import com.sm_arts.jibcon.app.setting.usercenter.UserCenter;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.lv_setting) ListView mSettingLv;
    @BindView(R.id.imageview_setting) ImageView mImageView;

    static final String[] sSettingList={"개인/보안","알림","고객센터"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSettingList);


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
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
