package com.sm_arts.jibcon.device.adddevice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDevice2Fragment extends Fragment {
    MakeDeviceListner mMakeDeviceListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMakeDeviceListener=(MakeDeviceListner) context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.device_add_device2_activity,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
