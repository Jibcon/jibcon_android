package com.sm_arts.jibcon.Settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;



public class SettingActivity extends AppCompatActivity {

    ListView mSettingLv;
    static final String[] mSettingList={"개인/보안","알림","고객센터"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSettingList);

        mSettingLv = (ListView)findViewById(R.id.Lv_setting);
        mSettingLv.setAdapter(adapter);
        /**/

        /* add onItemClickListener to ListView*/
        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;

            }
        }) ;


        /**/
    }



    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
