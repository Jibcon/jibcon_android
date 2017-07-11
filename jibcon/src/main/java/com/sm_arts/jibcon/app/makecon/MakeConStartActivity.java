package com.sm_arts.jibcon.app.makecon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.getothercon.GetOtherConActivity;
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

public class MakeConStartActivity extends BaseActivity {

    Button mMakeMyCon;
    Button mGetOtherCon;
    Button mSkip;

    private  void initLayout()
    {
        //mSkip=(Button)findViewById(R.id.Btn_MakeConStart_0);
        mMakeMyCon = (Button)findViewById(R.id.Btn_makeMyCon);
        mGetOtherCon =(Button)findViewById(R.id.Btn_getOtherCon);
        mSkip=(Button)findViewById(R.id.makeconstart_skip);
        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                startActivity(intent);
                finish();
            }
        });
        mMakeMyCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //집콘 만들기
                Intent intent = new Intent(getApplicationContext(),MakeCon0Fragment.class);
                startActivity(intent);
                finish();

            }
        });
        mGetOtherCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //집콘 초대받기
                Intent intent = new Intent(getApplicationContext(), GetOtherConActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_con_start);

        initLayout();
    }

}
