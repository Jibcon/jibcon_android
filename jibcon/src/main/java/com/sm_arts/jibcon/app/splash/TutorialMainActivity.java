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

public class TutorialMainActivity extends FragmentActivity {

    ImageButton mSkipButton;
    ViewPager mViewpager;
    int mMaxPage=4;

    Fragment mCurFragment = new Fragment();

    private void initLayout()
    {
        mViewpager = (ViewPager)findViewById(R.id.vp_tutorial);

    }
    private  class adapter extends FragmentPagerAdapter
    {
        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position<0||position>=4)
            {
                position=position%mMaxPage;
                return null;
            }
            switch (position)
            {
                case 0:
                    mCurFragment=new Tutorial1Fragment();

                    break;
                case 1:
                    mCurFragment=new Tutorial2Fragment();

                    break;
                case 2:
                    mCurFragment=new Tutorial3Fragment();

                    break;
                case 3:
                    mCurFragment=new Tutorial4Fragment();
                    break;
            }
            return mCurFragment;
        }

        @Override
        public int getCount() {
            return mMaxPage;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_tutorial_activity);

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
                    return Tutorial1Fragment.newInstance(pos);
                case 1:
                    return Tutorial1Fragment.newInstance(pos);
                case 2:
                    return Tutorial1Fragment.newInstance(pos);
                case 3:
                    return Tutorial1Fragment.newInstance(pos);
                default:
                    return Tutorial1Fragment.newInstance(0);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
