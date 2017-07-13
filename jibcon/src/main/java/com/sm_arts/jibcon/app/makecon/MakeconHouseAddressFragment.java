package com.sm_arts.jibcon.app.makecon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconHouseAddressFragment extends android.support.v4.app.Fragment{
    ImageButton mBefore;
    Button mNext;

    HouseInfoListener mHouseInfoListener;
    LinearLayout mLinearLayout;

    private void initLayout() {
        mBefore= (ImageButton)mLinearLayout.findViewById(R.id.Btn_MakeCon3_0);
        mNext =  (Button)mLinearLayout.findViewById(R.id.Btn_makeCon3_1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLinearLayout = (LinearLayout) inflater.inflate(R.layout.makecon_houseaddress_fragment,container,false);

        initLayout();

        mBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHouseInfoListener.getFragmentNum(-1);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHouseInfoListener.getFragmentNum(1);
            }
        });

        return mLinearLayout;
    }
}
