package com.sm_arts.jibcon.ui.additional.sidebar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAuthorityActivity extends BaseActivity {
    @BindView(R.id.lv_user_authority) ListView mSidebarUserAuthorityLv;
    @OnClick(R.id.imageview_sidebar_userauthority) void imageview_sidebar_userauthority() {
        finish();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_userauthorityactivity_activity);
        ButterKnife.bind(this);

        final String[] userauthorityOptionList = getResources().getStringArray(R.array.sidebar_userauthority_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, userauthorityOptionList);
        mSidebarUserAuthorityLv.setAdapter(adapter);

        mSidebarUserAuthorityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarUserAuthorityLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), settingClickedItem, Toast.LENGTH_LONG).show();
            }
        });
    }
}
