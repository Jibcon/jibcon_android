package com.sm_arts.jibcon.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.setting.SettingActivity;
import com.sm_arts.jibcon.setting.personsecure.PersonSecure;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class JibconAsk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_jibconask);

        ImageView mImageView = (ImageView)findViewById(R.id.imageview_setting_jibconask);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JibconAsk.this, UserCenter.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
