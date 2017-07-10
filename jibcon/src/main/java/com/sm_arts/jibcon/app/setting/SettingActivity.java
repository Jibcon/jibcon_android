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
import com.sm_arts.jibcon.app.setting.usercenter.UserCenterActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.lv_setting) ListView mSettingLv;
    @BindView(R.id.imageview_setting) ImageView mImageView;

    @BindString(R.string.setting_menu_1) String menu1;
    @BindString(R.string.setting_menu_2) String menu2;
    @BindString(R.string.setting_menu_3) String menu3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);

        String[] sSettingList={menu1,menu2,menu3};
        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSettingList);

        mSettingLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/

        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingLv.getItemAtPosition(position);

                if(settingClickedItem == menu1){
                    Intent intent = new Intent(SettingActivity.this, PersonSecure.class);
                    startActivity(intent);
                    finish();
                }
                else if(settingClickedItem==menu2){
                    Intent intent = new Intent(SettingActivity.this, Alarm.class);
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
