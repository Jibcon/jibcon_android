package com.sm_arts.jibcon.ui.splash.makecon;

import android.content.Intent;
import android.os.Bundle;

import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.splash.getothercon.GetOtherConActivity;
import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeconStartActivity extends BaseActivity {

    @OnClick(R.id.Btn_makeMyCon) void Btn_makeMyCon() {
        Intent intent = new Intent(getApplicationContext(),MakeconMainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.Btn_getOtherCon) void Btn_getOtherCon() {
        //집콘 초대받기
        Intent intent = new Intent(getApplicationContext(), GetOtherConActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.makeconstart_skip) void makeconstart_skip() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent); // Q. 왜 2개임?
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashmakecon_makeconstart_activity);
        ButterKnife.bind(this);
    }

}
