package com.sm_arts.jibcon.GetOtherCon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;

/**
 * Created by user on 2017-05-19.
 */

public class GetOtherConFinish extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getothercon_finish);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(GetOtherConFinish.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }
}
