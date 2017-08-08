package com.sm_arts.jibcon.ui.splash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sm_arts.jibcon.ui.splash.TutorialFragment;

/**
 * Created by jaeyoung on 8/3/17.
 */

public class TutorialMainPageAdapter extends FragmentPagerAdapter {
    private static final String TAG = "TutorialMainPageAdapter";

    private int mCount;

    public TutorialMainPageAdapter(FragmentManager fm, int count) {
        super(fm);
        mCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        return TutorialFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
