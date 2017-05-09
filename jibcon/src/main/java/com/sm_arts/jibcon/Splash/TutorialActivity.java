package com.sm_arts.jibcon.Splash;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sm_arts.jibcon.R;
import com.tsengvn.typekit.TypekitContextWrapper;

public class TutorialActivity extends AppCompatActivity {

    ViewPager viewpager;
    int MAX_PAGE=4;

    Fragment cur_fragment = new Fragment();

    private void initLayout()
    {
        viewpager =(ViewPager)findViewById(R.id.vp_tutorial);

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
                    cur_fragment=new Tutorial1();

                    break;
                case 1:
                    cur_fragment=new Tutorial2();

                    break;
                case 2:
                    cur_fragment=new Tutorial3();

                    break;
                case 3:
                    cur_fragment=new Tutorial4();
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

    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
