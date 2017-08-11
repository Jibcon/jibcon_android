package com.sm_arts.jibcon.ui.splash.tutorial;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017-04-09.
 */

public class TutorialFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "TutorialFragment";
    private static final String STRINGOF_TUTORIAL_PAGE = "NUMOFTUTORIALPAGE";

    @BindView(R.id.tv_tutorial_description) TextView tvDescription;
    @BindView(R.id.tv_tutorial_subdescription) TextView tvSubdescription;
    @BindView(R.id.iv_tutorial_sampleui) ImageView ivSampleui;
    @BindArray(R.array.tutorial_description_array)
    String[] stringsTutorialDescriptions;
    @BindArray(R.array.tutorial_subdescription_array)
    String[] stringsTutorialSubdescriptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_tutorialfragment_fragment, container, false);
        ButterKnife.bind(this, v);

        int tutorialNumber = getArguments().getInt(STRINGOF_TUTORIAL_PAGE);

        tvDescription.setText(stringsTutorialDescriptions[tutorialNumber]);
        tvSubdescription.setText(stringsTutorialSubdescriptions[tutorialNumber]);
        final TypedArray tutorialSampleUis = getResources().obtainTypedArray(R.array.drawable_tutorialsampleuis);
        ivSampleui.setImageResource(tutorialSampleUis.getResourceId(tutorialNumber, -1));
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
