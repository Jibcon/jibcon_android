package com.sm_arts.jibcon.app.setting.personsecure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.setting.SettingActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class PersonSecure extends AppCompatActivity {

    @BindView(R.id.lv_setting_personsecure) ListView mSettingPersonSecureLv;
    @BindString(R.string.setting_personsecure_menu_1) String menu1;
    @BindString(R.string.setting_personsecure_menu_2) String menu2;
    @BindString(R.string.setting_personsecure_menu_3) String menu3;
    @BindString(R.string.setting_personsecure_menu_4) String menu4;
    @BindString(R.string.setting_personsecure_menu_5) String menu5;

    @OnClick(R.id.imageview_setting_personsecure) void imageview_setting_personsecure(){
        Intent intent = new Intent(PersonSecure.this, SettingActivity.class);
        startActivity(intent);
        finish();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personsecure);
        ButterKnife.bind(this);


        String[] sSettingPersonSecureList={menu1,menu2,menu3,menu4,menu5};
        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSettingPersonSecureList);

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