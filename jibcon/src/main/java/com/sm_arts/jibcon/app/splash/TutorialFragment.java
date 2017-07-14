package com.sm_arts.jibcon.app.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-04-09.
 */

public class TutorialFragment extends android.support.v4.app.Fragment{
    private static final String STRINGOF_TUTORIAL_PAGE="numofTutorialPage";

    @BindView(R.id.tutorial_title_tv) TextView tutorialTitleTv;
    @BindView(R.id.tutorial_sub_tv) TextView tutorialSubTv;
    @BindView(R.id.tutorial_iv) ImageView tutorialIv;
    @BindView(R.id.tutorial_makejibcon_btn) Button makejibconBtn;
    @OnClick(R.id.tutorial_makejibcon_btn) void makejibconListener(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.splash_tutorial_fragment, container, false);
        ButterKnife.bind(this,v);

        int tutorialNumber = getArguments().getInt(STRINGOF_TUTORIAL_PAGE);

        makejibconBtn.setVisibility(View.INVISIBLE);

        switch (tutorialNumber){
            case 0:
                tutorialTitleTv.setText(R.string.tutorial_title1);
                tutorialSubTv.setText(R.string.tutorial_sub1);
                tutorialIv.setImageResource(R.drawable.splash_image);
                break;
            case 1:
                tutorialTitleTv.setText(R.string.tutorial_title2);
                tutorialSubTv.setText(R.string.tutorial_sub2);
                tutorialIv.setImageResource(R.drawable.splash_image);
                break;
            case 2:
                tutorialTitleTv.setText(R.string.tutorial_title3);
                tutorialSubTv.setText(R.string.tutorial_sub3);
                tutorialIv.setImageResource(R.drawable.splash_image);
                break;
            case 3:
                tutorialTitleTv.setText(R.string.tutorial_title4);
                tutorialSubTv.setText(R.string.tutorial_sub4);
                tutorialIv.setImageResource(R.drawable.splash_image);
                makejibconBtn.setVisibility(View.VISIBLE);
                break;
        }
        return v;
    }

    public static Fragment newInstance(int number) {
        Fragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt(STRINGOF_TUTORIAL_PAGE, number);
        fragment.setArguments(args);
        return fragment;
    }
}