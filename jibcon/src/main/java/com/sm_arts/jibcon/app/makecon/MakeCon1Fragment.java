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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon1Fragment extends android.support.v4.app.Fragment {
    HouseInfoListener mHouseInfoListener;
    String mHousename;
    String mUsername;
    String mHouseintro;

    @BindView(R.id.Btn_makeCon1_1) Button mNext;
    @OnClick(R.id.Btn_makeCon1_1) void Btn_makeCon1_1(){
        mHousename=mEditHousename.getText().toString();
        mHouseintro=mEditHouseintro.getText().toString();
        mUsername=mEditUsername.getText().toString();
        //  ((mHouseInfoListener)mActivity).getHouseName(mHousename);
        mHouseInfoListener.getHouseIntro(mHouseintro);
        mHouseInfoListener.getHouseName(mHousename);
        mHouseInfoListener.getUserName(mUsername);
        mHouseInfoListener.getFragmentNum(1);
        //  Intent intent = new Intent(getActivity().getApplicationContext(), MakeCon2Fragment.class);
        //  startActivity(intent);
    }
    @BindView(R.id.Btn_MakeCon1_0) ImageButton mBefore;
    @OnClick(R.id.Btn_MakeCon1_0) void Btn_MakeCon1_0(){
        mHouseInfoListener.getFragmentNum(-1);
    }
    @BindView(R.id.EditTxt_makecon1_1) EditText mEditHousename;
    @BindView(R.id.EditTxt_makecon1_2) EditText mEditUsername;
    @BindView(R.id.EditTxt_makecon1_3) EditText mEditHouseintro;

    LinearLayout mLinearLayout;
    int mFragmentNum;

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

    private void initLayout()
    {
        mFragmentNum = 0;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mLinearLayout = (LinearLayout)inflater.inflate(R.layout.makecon_housename_fragment,container,false);
        initLayout();
        ButterKnife.bind(this,mLinearLayout);

        mHousename=mEditHousename.getText().toString();
        mHouseintro=mEditHouseintro.getText().toString();
        mUsername=mEditUsername.getText().toString();

        return mLinearLayout;
    }
}
