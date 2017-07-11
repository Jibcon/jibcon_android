package com.sm_arts.jibcon.app.getothercon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;

/**
 * Created by user on 2017-05-19.
 */

public class GetOtherConFinishActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getothercon_finish_activity);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(GetOtherConFinishActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }

}
