package com.sm_arts.jibcon.MakeCon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sm_arts.jibcon.MainActivity;
import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;

/*
* 170511 chanjoo
*
* 1. 스토리 보드 상 skip버튼이 포함되어있지 않아 삭제함.
* 2.
*
* */

public class MakeConStartActivity extends AppCompatActivity {

    Button makeMyCon;
    Button getOtherCon;
    //Button skip;

    private  void initLayout()
    {
        //skip=(Button)findViewById(R.id.Btn_MakeConStart_0);
        makeMyCon = (Button)findViewById(R.id.Btn_makeMyCon);
        getOtherCon =(Button)findViewById(R.id.Btn_getOtherCon);

        makeMyCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //집콘 만들기
                Intent intent = new Intent(getApplicationContext(),MakeCon0.class);
                startActivity(intent);
                finish();

            }
        });
        getOtherCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //집콘 초대받기

            }
        });
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_con_start);



        initLayout();
        /* skip 버튼 삭제 (승인 대기 중)
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        */
    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
