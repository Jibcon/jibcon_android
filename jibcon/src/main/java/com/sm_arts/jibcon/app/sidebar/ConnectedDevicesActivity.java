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

public class ConnectedDevicesActivity extends BaseActivity {
    @OnClick(R.id.imageview_sidebar_connecteddevices) void imageview_sidebar_connecteddevices() {
        finish();
    }

    @BindView(R.id.lv_connected_device) ListView mSidebarConnectedDevicesLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_connecteddevicesactivity_activity);
        ButterKnife.bind(this);

        final String[] connecteddevicesOptionList = getResources().getStringArray(R.array.sidebar_connecteddevices_array);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, connecteddevicesOptionList);
        mSidebarConnectedDevicesLv.setAdapter(adapter);

        mSidebarConnectedDevicesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarConnectedDevicesLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        });
    }
}
