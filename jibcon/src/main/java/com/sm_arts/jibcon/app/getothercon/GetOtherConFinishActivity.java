package com.sm_arts.jibcon.app.getothercon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by user on 2017-05-19.
 */

public class GetOtherConFinishActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getothercon_finish);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(GetOtherConFinishActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }

}
