package com.sm_arts.jibcon.Splash;

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

import com.sm_arts.jibcon.Login.LoginActivity;
import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;

public class TutorialActivity extends AppCompatActivity {

    ImageButton skip_button;
    ViewPager viewpager;
    int MAX_PAGE=4;

    Fragment cur_fragment = new Fragment();

    private void initLayout()
    {
        viewpager = (ViewPager)findViewById(R.id.vp_tutorial);

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
                position=position%MAX_PAGE;
                return null;
            }
            switch (position)
            {
                case 0:
                    cur_fragment=new Tutorial1Fragment();

                    break;
                case 1:
                    cur_fragment=new Tutorial2Fragment();

                    break;
                case 2:
                    cur_fragment=new Tutorial3Fragment();

                    break;
                case 3:
                    cur_fragment=new Tutorial4Fragment();
                    break;
            }
            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        initLayout();

        viewpager.setAdapter(new adapter(getSupportFragmentManager()));
        viewpager.setOffscreenPageLimit(3);

        // 튜토리얼 스킵 버튼
        skip_button = (ImageButton) findViewById(R.id.btn_skip_tutorial);
        skip_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TutorialActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
