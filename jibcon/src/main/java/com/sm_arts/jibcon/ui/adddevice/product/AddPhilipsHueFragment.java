package com.sm_arts.jibcon.ui.adddevice.product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.ui.adddevice.HueRegisterManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017-11-30.
 */

public class AddPhilipsHueFragment extends Fragment {
    private AddDeviceListner mMakeDeviceListener;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMakeDeviceListener = (AddDeviceListner) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMakeDeviceListener = null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.adddevice_addphilps_activity, container, false);

        mUnbinder = ButterKnife.bind(this, layout);
        initLayout();
        getInternalAddress();


        return layout;
    }

    private void initLayout() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void getInternalAddress() {
        HueRegisterManager.getInstance().getInternalAddress();
    }


}
