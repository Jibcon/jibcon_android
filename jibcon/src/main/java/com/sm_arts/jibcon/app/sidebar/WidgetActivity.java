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

public class WidgetActivity extends BaseActivity {
    @BindString(R.string.sidebar_widget_menu_1) String menu1;
    @BindString(R.string.sidebar_widget_menu_2) String menu2;
    @BindString(R.string.sidebar_widget_menu_3) String menu3;
    @BindString(R.string.sidebar_widget_menu_4) String menu4;
    @BindView(R.id.lv_widget) ListView mSidebarWidgetLV;
    @OnClick(R.id.imageview_sidebar_widget) void imageview_sidebar_widget() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_widgetactivity_activity);
        ButterKnife.bind(this);

        String[] sSidebarWidgetList = {
                menu1,menu2,menu3,menu4
        };

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, sSidebarWidgetList);
        mSidebarWidgetLV.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarWidgetLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarWidgetLV.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        });


    }
}
