package com.sm_arts.jibcon.app.sidebar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJibconActivity extends BaseActivity {
    @BindView(R.id.lv_my_jibcon) ListView mSidebarMyJibconLv;
    @BindString(R.string.sidebar_myjibcon_menu_1) String menu1;
    @BindString(R.string.sidebar_myjibcon_menu_2) String menu2;
    @BindString(R.string.sidebar_myjibcon_menu_3) String menu3;
    @OnClick(R.id.imageview_sidebar_myjibcon) void imageview_sidebar_myjibcon(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_myjibconactivity_activity);
        ButterKnife.bind(this);

        String[] sSidebarMyJibconList = {
                menu1,menu2,menu3
        };

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSidebarMyJibconList);
        mSidebarMyJibconLv.setAdapter(adapter);

        /* add onItemClickListener to ListView*/
        mSidebarMyJibconLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarMyJibconLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        });
    }
}
