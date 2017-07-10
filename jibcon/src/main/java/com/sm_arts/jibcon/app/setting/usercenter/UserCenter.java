package com.sm_arts.jibcon.app.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.setting.SettingActivity;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class UserCenter extends AppCompatActivity {

    ListView mSettingUserCenterLv;
    static final String[] sSettingUserCenterList={"제조업체 문의","FAQ","집콘 문의하기"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_usercenter);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSettingUserCenterList);

        mSettingUserCenterLv = (ListView)findViewById(R.id.lv_setting_usercenter);

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
                    Intent intent = new Intent(UserCenter.this, Faq.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(UserCenter.this, JibconAsk.class);
                    startActivity(intent);
                }
            }
        }) ;

        ImageView mImageView = (ImageView)findViewById(R.id.imageview_setting_usercenter);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCenter.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}