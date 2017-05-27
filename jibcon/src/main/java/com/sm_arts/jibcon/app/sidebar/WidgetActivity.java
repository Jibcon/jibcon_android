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

public class WidgetActivity extends AppCompatActivity {

    ListView mSidebarWidgetLV;
    static final String[] mSidebarWidgetList={"On/Off 위젯 만들기","루틴 위젯 만들기","툴바 만들기","데이터 위젯 만들기"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_widget);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSidebarWidgetList);

        mSidebarWidgetLV = (ListView)findViewById(R.id.listview_widget);

        mSidebarWidgetLV.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarWidgetLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarWidgetLV.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

        ImageView mImageView = (ImageView)findViewById(R.id.imageview_sidebar_widget);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
