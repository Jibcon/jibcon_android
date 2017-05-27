package com.sm_arts.jibcon.sidebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;

public class UserAuthorityActivity extends AppCompatActivity {

    ListView mSidebarUserAuthorityLV;
    static final String[] mSidebarUserAuthorityList={"집에 연결된 사용자 관리","내 집콘에 초대하기","초대받은 목록"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_about_jibcon);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSidebarUserAuthorityList);

        mSidebarUserAuthorityLV = (ListView)findViewById(R.id.listview_user_authority);

        mSidebarUserAuthorityLV.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarUserAuthorityLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarUserAuthorityLV.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

    }
}
