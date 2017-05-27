package com.sm_arts.jibcon.setting.personsecure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person;
import com.sm_arts.jibcon.Login.LoginActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.Splash.TutorialActivity;
import com.sm_arts.jibcon.setting.SettingActivity;
import com.sm_arts.jibcon.setting.alarm.Alarm;
import com.sm_arts.jibcon.setting.usercenter.UserCenter;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class PersonSecure extends AppCompatActivity {
    private static final String TAG = "kimwoojinlog ";
    ListView mSettingPersonSecureLv;
    static final String[] mSettingList={"프로필 설정","암호 설정","지문 설정","정보제공 동의서", "집콘 탈퇴하기"};

    Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSettingList);

        mSettingPersonSecureLv = (ListView)findViewById(R.id.Lv_setting);

        mSettingPersonSecureLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSettingPersonSecureLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingPersonSecureLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;
        mToolBar=(Toolbar)findViewById(R.id.toolbar_setting_personsecure);

    }



    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}