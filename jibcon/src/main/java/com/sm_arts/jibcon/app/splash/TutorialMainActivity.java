package com.sm_arts.jibcon.app.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.login.LoginActivity;
import com.sm_arts.jibcon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorialMainActivity extends BaseActivity {
    @OnClick(R.id.btn_skip_tutorial) void skipTutorialListener(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_tutorial_activity);
        ButterKnife.bind(this);

        ViewPager pager = (ViewPager) findViewById(R.id.vp_tutorial);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0:
                    return TutorialFragment.newInstance(pos);
                case 1:
                    return TutorialFragment.newInstance(pos);
                case 2:
                    return TutorialFragment.newInstance(pos);
                case 3:
                    return TutorialFragment.newInstance(pos);
                default:
                    return TutorialFragment.newInstance(0);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
