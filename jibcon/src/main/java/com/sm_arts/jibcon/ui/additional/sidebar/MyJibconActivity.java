package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconEndFragment;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconHouseaddressFragment;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconHousenameFragment;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconHousetypeFragment;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJibconActivity extends BaseActivity {
    @BindView(R.id.lv_my_jibcon)
    ListView mSidebarMyJibconLv;

    Fragment mMakecon1;
    Fragment mMakecon2;
    Fragment mMakecon3;
    Fragment mMakecon4;


    @OnClick(R.id.imageview_sidebar_myjibcon)
    void imageview_sidebar_myjibcon() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_myjibconactivity_activity);
        ButterKnife.bind(this);
        mMakecon1 = new MakeconHousenameFragment();
        mMakecon2 = new MakeconHousetypeFragment();
        mMakecon3 = new MakeconHouseaddressFragment();
        mMakecon4 = new MakeconEndFragment();


        final String[] myjibconOptionList = getResources().getStringArray(R.array.sidebar_myjibcon_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myjibconOptionList);
        mSidebarMyJibconLv.setAdapter(adapter);

        mSidebarMyJibconLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarMyJibconLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), settingClickedItem, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), MakeconMainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), HomeSettingActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), HomeEnvActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), SetAddressActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 4:
                        intent = new Intent(getApplicationContext(), DeleteHomeActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }
}
