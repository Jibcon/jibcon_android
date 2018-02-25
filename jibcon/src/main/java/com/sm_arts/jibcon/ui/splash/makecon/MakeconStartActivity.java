package com.sm_arts.jibcon.ui.splash.makecon;

import android.content.Intent;
import android.os.Bundle;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeconStartActivity extends BaseActivity {

    @OnClick(R.id.Btn_makeMyCon) void Btn_makeMyCon() {
        Intent intent = new Intent(getApplicationContext(),MakeconMainActivity.class);
        startActivity(intent);
        finish();
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
