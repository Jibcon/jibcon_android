package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSettingActivity extends AppCompatActivity {
    @BindView(R.id.Btn_makeCon1_1)
    Button Btn_makeCon1_1;

    @BindView(R.id.imageview_sidebar_myjibcon)
    ImageView imageview_sidebar_myjibcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_homesettingactivity_activity);
        ButterKnife.bind(this);


        Btn_makeCon1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });

        imageview_sidebar_myjibcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });

    }
}
