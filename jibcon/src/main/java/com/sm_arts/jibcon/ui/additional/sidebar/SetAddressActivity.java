package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconHouseaddressFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetAddressActivity extends FragmentActivity {
    @BindView(R.id.setaddress_addressnum) TextView setaddress_addressnum;
    @BindView(R.id.setaddress_addressnum2) TextView setaddress_addressnum2;
    @BindView(R.id.setaddress_addressnum3) TextView setaddress_addressnum3;
    @BindView(R.id.setaddress_search_button)
    Button setaddress_search_button;

    @BindView(R.id.siderbar_setaddressactivity_sidebarback)
    ImageView siderbar_setaddressactivity_sidebarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_setaddressactivity_activity);
        ButterKnife.bind(this);


        siderbar_setaddressactivity_sidebarback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });


        setaddress_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mMakecon4= new MakeconHouseaddressFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_makecon0, mMakecon4).commit();
            }
        });
    }



}
