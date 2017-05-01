package com.sm_arts.jibcon.Cheatkey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sm_arts.jibcon.R;

/**
 * Edited by ChanJoo on 2017-04-13.
 */

public class TrickMenuActivity extends Fragment{
    // Viewpager
    ViewPager vp;

    public TrickMenuActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.menu_trick, container, false);

        vp = (ViewPager)layout.findViewById(R.id.vp_cheatkey);

        /* ↓Acitve & Passive Button↓ */
        Button btn_active = (Button) layout.findViewById(R.id.btn_active);
        Button btn_passive = (Button) layout.findViewById(R.id.btn_passive);

        //vp.setAdapter(new pagerAdapter(getChildFragmentManager())); // getSupportFragmentManager에서 수정
        //vp.setCurrentItem(0);

        btn_active.setOnClickListener(movePageListener);
        btn_active.setTag(0);
        btn_passive.setOnClickListener(movePageListener);
        btn_passive.setTag(1);
        /* ↑Acitve & Passive Button↑ */

        return layout;
    }

    /* ↓뷰 페이저(액티비티 슬라이드)↓ */
    View.OnClickListener movePageListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            Log.w("CJ","movePageListener");
            switch(position){
                case 0:
                    //return new TrickActive();
                case 1:
                    //return new TrickPassive();
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            return 2;
        }
    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */
}
