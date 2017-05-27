package com.sm_arts.jibcon.sidebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;

public class ConnectedDevicesActivity extends AppCompatActivity {

    ListView mSidebarConnectedDevicesLV;
    static final String[] mSidebarConnectedDeviceList={"Company         Device",
                                                    "Company         Device",
                                                    "Company         Device"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_connected_devices);

        /* add String[] to ListView*/
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mSidebarConnectedDeviceList);

        mSidebarConnectedDevicesLV = (ListView)findViewById(R.id.listview_connected_device);

        mSidebarConnectedDevicesLV.setAdapter(adapter);
        /* add onItemClickListener to ListView*/
        mSidebarConnectedDevicesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                String settingClickedItem = (String) mSidebarConnectedDevicesLV.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),settingClickedItem,Toast.LENGTH_LONG).show();
            }
        }) ;

        ImageView mImageView = (ImageView)findViewById(R.id.imageview_sidebar_connecteddevices);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
