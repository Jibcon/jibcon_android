package com.sm_arts.jibcon.sidebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;

public class MyJibconActivity extends AppCompatActivity {

    ListView mSidebarMyJibconLV;
    static final String[] mSidebarMyJibconList={"집소개 수정","주거 환경","주소 설정"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_about_jibcon);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSidebarMyJibconList);

        mSidebarMyJibconLV = (ListView)findViewById(R.id.listview_my_jibcon);

        mSidebarMyJibconLV.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarMyJibconLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarMyJibconLV.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

    }
}
