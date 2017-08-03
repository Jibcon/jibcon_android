package com.sm_arts.jibcon.ui.splash.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.splash.TutorialFragment;

import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * Created by jaeyoung on 8/3/17.
 */

public class TutorialMainPageAdapter extends FragmentPagerAdapter {
    @BindArray(R.array.tutorial_subdescription_array)
    String[] tutorialSubdescriptions;

    private int mCount;

    private static final String TAG = "TutorialMainPageAdapter";

    public TutorialMainPageAdapter(FragmentManager fm, int count) {
        super(fm);
        mCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem() called with: position = [" + position + "]");
        return TutorialFragment.newInstance((position + 1) % mCount);
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
