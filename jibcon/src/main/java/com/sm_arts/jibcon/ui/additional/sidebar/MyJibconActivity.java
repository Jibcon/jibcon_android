package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJibconActivity extends BaseActivity {
    @BindView(R.id.lv_my_jibcon) ListView mSidebarMyJibconLv;
    @OnClick(R.id.imageview_sidebar_myjibcon) void imageview_sidebar_myjibcon() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_myjibconactivity_activity);
        ButterKnife.bind(this);

        final String[] myjibconOptionList = getResources().getStringArray(R.array.sidebar_myjibcon_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myjibconOptionList);
        mSidebarMyJibconLv.setAdapter(adapter);

        mSidebarMyJibconLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarMyJibconLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), settingClickedItem, Toast.LENGTH_LONG).show();
                switch (position)
                {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Intent intent = new Intent(getApplicationContext(), MakeconMainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 4 :
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
