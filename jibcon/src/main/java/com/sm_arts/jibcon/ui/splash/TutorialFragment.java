package com.sm_arts.jibcon.ui.splash;

import android.content.Intent;
import android.content.res.TypedArray;
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

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-04-09.
 */

public class TutorialFragment extends android.support.v4.app.Fragment {
    private static final String STRINGOF_TUTORIAL_PAGE = "numofTutorialPage";

    @BindView(R.id.tv_tutorial_description) TextView tutorialTitleTv;
    @BindView(R.id.tv_tutorial_subdescription) TextView tutorialSubTv;
    @BindView(R.id.iv_tutorial_sampleui) ImageView tutorialIv;
    @BindView(R.id.btn_tutorial_makejibcon) Button makejibconBtn;
    @BindArray(R.array.tutorial_description_array)
    String[] tutorialDescriptions;
    @BindArray(R.array.tutorial_subdescription_array)
    String[] tutorialSubdescriptions;

    @OnClick(R.id.btn_tutorial_makejibcon) void makejibconListener() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_tutorialfragment_fragment, container, false);
        ButterKnife.bind(this, v);

        int tutorialNumber = getArguments().getInt(STRINGOF_TUTORIAL_PAGE);

        makejibconBtn.setVisibility(View.INVISIBLE);

        tutorialTitleTv.setText(tutorialDescriptions[tutorialNumber]);
        tutorialSubTv.setText(tutorialSubdescriptions[tutorialNumber]);
        final TypedArray tutorialSampleUis = getResources().obtainTypedArray(R.array.drawable_tutorialsampleuis);
        tutorialIv.setImageResource(tutorialSampleUis.getResourceId(tutorialNumber, -1));
        tutorialSampleUis.recycle();

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
