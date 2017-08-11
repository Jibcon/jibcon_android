package com.sm_arts.jibcon.ui.additional.setting.personsecure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.additional.setting.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class PersonSecureActivity extends BaseActivity {
    @BindView(R.id.lv_setting_personsecure) ListView mSettingPersonSecureLv;
    @OnClick(R.id.imageview_setting_personsecure) void imageview_setting_personsecure() {
        Intent intent = new Intent(PersonSecureActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personsecureactivity_activity);
        ButterKnife.bind(this);

        final String[] personsecureOptionList = getResources().getStringArray(R.array.setting_personsecure_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, personsecureOptionList);

        mSettingPersonSecureLv.setAdapter(adapter);

        mSettingPersonSecureLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSettingPersonSecureLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }

        });
    }
}