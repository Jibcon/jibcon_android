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

public class UserAuthorityActivity extends BaseActivity {


    @BindView(R.id.lv_user_authority) ListView mSidebarUserAuthorityLv;
    @BindString(R.string.sidebar_userauthority_menu_1) String menu1;
    @BindString(R.string.sidebar_userauthority_menu_2) String menu2;
    @BindString(R.string.sidebar_userauthority_menu_3) String menu3;
    @OnClick(R.id.imageview_sidebar_userauthority) void imageview_sidebar_userauthority(){
        finish();}
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_userauthorityactivity_activity);
        ButterKnife.bind(this);

        String[] sSidebarUserAuthorityList={menu1,menu2,menu3};

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSidebarUserAuthorityList);
        mSidebarUserAuthorityLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarUserAuthorityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarUserAuthorityLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;


    }
}
