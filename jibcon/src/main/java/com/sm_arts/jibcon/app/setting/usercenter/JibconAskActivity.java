package com.sm_arts.jibcon.app.setting.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;

/**
 * Created by woojinkim on 2017. 5. 20..
 */

public class JibconAskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_jibconask);

        ImageView imageView = (ImageView)findViewById(R.id.imageview_setting_jibconask);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JibconAskActivity.this, UserCenterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
