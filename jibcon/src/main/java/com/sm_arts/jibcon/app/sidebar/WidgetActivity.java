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
    @BindView(R.id.lv_widget) ListView mSidebarWidgetLV;
    @OnClick(R.id.imageview_sidebar_widget) void imageview_sidebar_widget() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_widgetactivity_activity);
        ButterKnife.bind(this);

        final String[] widgetOptionList = getResources().getStringArray(R.array.sidebar_widget_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, widgetOptionList);
        mSidebarWidgetLV.setAdapter(adapter);

        mSidebarWidgetLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarWidgetLV.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        });
    }
}
