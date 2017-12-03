package com.sm_arts.jibcon.ui.splash.makecon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.additional.sidebar.MyJibconActivity;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconHousenameFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "MakeconHousenameFragment";
    private HouseInfoListener mHouseInfoListener;
    private String mHousename;
    private String mUsername;
    private String mHouseintro;
    private Button mNext;
    private ImageButton mBefore;
    private TextView mBarName;

    private RelativeLayout mLinearLayout;
    private EditText mEditHousename;
    private EditText mEditUsername;
    private EditText mEditHouseintro;
    private Activity mActivity;
    private int mFragmentNum;
    ImageView BackImage ;



    private void initLayout() {
        mNext = (Button) mLinearLayout.findViewById(R.id.Btn_makeCon1_1);
        mEditHousename = (EditText) mLinearLayout.findViewById(R.id.EditTxt_makecon1_1);
       // mEditUsername = (EditText) mLinearLayout.findViewById(R.id.EditTxt_makecon1_2);
        mEditHouseintro = (EditText) mLinearLayout.findViewById(R.id.EditTxt_makecon1_3);

        BackImage = (ImageView) mLinearLayout.findViewById(R.id.imageview_sidebar_myjibcon);
        mFragmentNum = 0;
        mBarName = (TextView) mLinearLayout.findViewById(R.id.bar_name);
    }




    @Override
    public void onAttach(Activity mActivity) {
        super.onAttach(mActivity);
        try {
            mHouseInfoListener = (HouseInfoListener) mActivity;
        }
        catch (Exception e)
        {
            Log.d("onAttach() error","onAttachError!!\n\n\n\n\n");
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLinearLayout = (RelativeLayout)inflater.inflate(R.layout.splashmakecon_makeconhousename_fragment,container,false);

        initLayout();

        mHousename = mEditHousename.getText().toString();
        mHouseintro = mEditHouseintro.getText().toString();
       // mUsername = mEditUsername.getText().toString();



        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHousename = mEditHousename.getText().toString();
                mHouseintro = mEditHouseintro.getText().toString();
             //   mUsername = mEditUsername.getText().toString();

                //  ((mHouseInfoListener)mActivity).setHouseName(mHousename);
                mHouseInfoListener.setHouseIntro(mHouseintro);
                mHouseInfoListener.setHouseName(mHousename);
           //     mHouseInfoListener.setUserName(mUsername);
                mHouseInfoListener.setFragmentNum(1);

//                Intent intent = new Intent(getActivity().getApplicationContext(), MakeconHousetypeFragment.class);
//                startActivity(intent);
            }
        });


        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getActivity().getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });



        mBarName.setText("집 이름"); // sorry for hard-coding

        return mLinearLayout;
    }
}
