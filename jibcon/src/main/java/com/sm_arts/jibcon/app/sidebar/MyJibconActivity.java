package com.sm_arts.jibcon.app.sidebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;

public class MyJibconActivity extends AppCompatActivity {

    ListView mSidebarMyJibconLv;
    static final String[] sSidebarMyJibconList={"집소개 수정","주거 환경","주소 설정"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_my_jibcon);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSidebarMyJibconList);

        mSidebarMyJibconLv = (ListView)findViewById(R.id.listview_my_jibcon);

        mSidebarMyJibconLv.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarMyJibconLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarMyJibconLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

        ImageView mImageView = (ImageView)findViewById(R.id.imageview_sidebar_myjibcon);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
