package com.sm_arts.jibcon.app.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.login.LoginActivity;
import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;

public class TutorialActivity extends BaseActivity {

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
        setContentView(R.layout.activity_tutorial);

        initLayout();

        mViewpager.setAdapter(new adapter(getSupportFragmentManager()));
        mViewpager.setOffscreenPageLimit(3);

        // 튜토리얼 스킵 버튼
        mSkipButton = (ImageButton) findViewById(R.id.btn_skip_tutorial);
        mSkipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TutorialActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
