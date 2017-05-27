package com.sm_arts.jibcon.Settings.person_secure;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class Person_Secure extends AppCompatActivity {

    ListView mSettingPersonSecureLv;
    static final String[] mSettingPersonSecureList={"프로필 설정 ","암호 설정 ","집콘 설정 ","정보제공 동의서 ","집콘 탈퇴하기 "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personal_secure);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSettingPersonSecureList);

        mSettingPersonSecureLv = (ListView)findViewById(R.id.Lv_setting_personal_secure);

        mSettingPersonSecureLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSettingPersonSecureLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSettingPersonSecureLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

    }



    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
