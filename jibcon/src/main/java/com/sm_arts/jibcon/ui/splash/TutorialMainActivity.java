package com.sm_arts.jibcon.ui.splash;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.login.LoginActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.splash.adapter.TutorialMainPageAdapter;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorialMainActivity extends BaseActivity {
    private static final String TAG = "TutorialMainActivity";

    @BindView(R.id.progress)
    ImageView mIvProgress;
    @BindArray(R.array.drawable_progresscircles)
    TypedArray drawableProgresscircles;

    @OnClick(R.id.btn_skip_tutorial)
    void skipTutorialListener() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_tutorialmainactivity_activity);
        ButterKnife.bind(this);

        ViewPager pager = (ViewPager) findViewById(R.id.vp_tutorial);
        Log.d(TAG, "onCreate: drawableProgresscircles.length="+ drawableProgresscircles.length());
        pager.setAdapter(new TutorialMainPageAdapter(getSupportFragmentManager(), drawableProgresscircles.length()));

        Log.d(TAG, "onCreate: " + drawableProgresscircles);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                showIvProgress(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showIvProgress(int position) {
        mIvProgress.setImageResource(drawableProgresscircles.getResourceId(position, -1));
    }
}
