package com.sm_arts.jibcon.ui.splash.makecon;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

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

    private RelativeLayout mRelativelayout;
    private EditText mEditHousename;

    private Activity mActivity;
    private int mFragmentNum;



    private void initLayout() {
        mNext = (Button) mRelativelayout.findViewById(R.id.Btn_makeCon1_1);
        mEditHousename = (EditText) mRelativelayout.findViewById(R.id.EditTxt_makecon1_1);
        mFragmentNum = 0;
        mBarName = (TextView) mRelativelayout.findViewById(R.id.bar_name);
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
        mRelativelayout = (RelativeLayout)inflater.inflate(R.layout.splashmakecon_makeconhousename_fragment,container,false);
      
        initLayout();

        mHousename = mEditHousename.getText().toString();




        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHousename = mEditHousename.getText().toString();

                //  ((mHouseInfoListener)mActivity).setHouseName(mHousename);
                mHouseInfoListener.setHouseIntro(mHouseintro);
                mHouseInfoListener.setHouseName(mHousename);
                mHouseInfoListener.setUserName(mUsername);
                mHouseInfoListener.setFragmentNum(1);

//                Intent intent = new Intent(getActivity().getApplicationContext(), MakeconHousetypeFragment.class);
//                startActivity(intent);
            }
        });

        mBarName.setText("집 이름"); // sorry for hard-coding

        return mRelativelayout;
    }
}
