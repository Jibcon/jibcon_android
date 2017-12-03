package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeleteHomeActivity extends BaseActivity {
    @BindView(R.id.delete_my_jibcon)
    ListView delete_my_jibcon;

    @BindView(R.id.sidebar_back)
    ImageView sidebar_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_deletehomeactivity_activity);
        ButterKnife.bind(this);

        final String[] myjibconDeleteOptionList = getResources().getStringArray(R.array.sidebar_myjibcon_delete_array);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myjibconDeleteOptionList);
        delete_my_jibcon.setAdapter(adapter);

        delete_my_jibcon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) delete_my_jibcon.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), settingClickedItem, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                      //  Intent intent = new Intent(getApplicationContext(), HomeSettingActivity.class);
                        //startActivity(intent);
                       // finish();
                      //  break;
                    case 1:

                    case 2:

                    case 3:

                }
            }
        });


        sidebar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });

    }


}
