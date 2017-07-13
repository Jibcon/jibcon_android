package com.sm_arts.jibcon.app.makecon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconHousenameFragment extends android.support.v4.app.Fragment {
    HouseInfoListener mHouseInfoListener;
    String mHousename;
    String mUsername;
    String mHouseintro;
    Button mNext;
    ImageButton mBefore;
    LinearLayout mLinearLayout;
    EditText mEditHousename;
    EditText mEditUsername;
    EditText mEditHouseintro;
    Activity mActivity;
    int mFragmentNum;



    private void initLayout()
    {
        mNext = (Button)mLinearLayout.findViewById(R.id.Btn_makeCon1_1);
        mEditHousename = (EditText)mLinearLayout.findViewById(R.id.EditTxt_makecon1_1);
        mEditUsername = (EditText)mLinearLayout.findViewById(R.id.EditTxt_makecon1_2);
        mEditHouseintro=(EditText)mLinearLayout.findViewById(R.id.EditTxt_makecon1_3);
        mFragmentNum = 0;
        mBefore=(ImageButton)mLinearLayout.findViewById(R.id.Btn_MakeCon1_0);

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

        mLinearLayout = (LinearLayout)inflater.inflate(R.layout.makecon_housename_fragment,container,false);
        initLayout();

        mHousename=mEditHousename.getText().toString();
        mHouseintro=mEditHouseintro.getText().toString();
        mUsername=mEditUsername.getText().toString();


        mBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHouseInfoListener.getFragmentNum(-1);
            }
        });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHousename=mEditHousename.getText().toString();
                mHouseintro=mEditHouseintro.getText().toString();
                mUsername=mEditUsername.getText().toString();

                //  ((mHouseInfoListener)mActivity).getHouseName(mHousename);
                mHouseInfoListener.getHouseIntro(mHouseintro);
                mHouseInfoListener.getHouseName(mHousename);
                mHouseInfoListener.getUserName(mUsername);
                mHouseInfoListener.getFragmentNum(1);

//                Intent intent = new Intent(getActivity().getApplicationContext(), MakeconHousetypeFragment.class);
//                startActivity(intent);
            }
        });
        return mLinearLayout;
    }
}
