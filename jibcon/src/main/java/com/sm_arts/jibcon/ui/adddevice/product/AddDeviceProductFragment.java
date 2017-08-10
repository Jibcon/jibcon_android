package com.sm_arts.jibcon.ui.adddevice.product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.utils.consts.Configs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDeviceProductFragment extends Fragment {
    private static final String TAG = "AddDeviceProductFragmen";

    @BindView(R.id.spinner_devicecompany)
    Spinner mSpinnerDevicecompany;
    @BindView(R.id.spinner_devicetype)
    Spinner mSpinnerDevicetype;
    @BindView(R.id.btnNext)
    Button mBtnNext;

    private Unbinder mUnbinder;
    private AddDeviceListner mMakeDeviceListener;

    private void initLayout() {
        ArrayAdapter<String> devicecompanySpinnerAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item,
                Configs.DEVICES_SUPPORTABLE.DEVICECOM_CHOICES
        );
        ArrayAdapter<String> devicetypeSpinnerAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item,
                Configs.DEVICES_SUPPORTABLE.DEVICETYPE_CHOICES
        );

        devicecompanySpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        devicetypeSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mSpinnerDevicecompany.setAdapter(devicecompanySpinnerAdapter);
        mSpinnerDevicetype.setAdapter(devicetypeSpinnerAdapter);

        mBtnNext.setOnClickListener(
                (v) -> mMakeDeviceListener.nextPage(1)
        );

        mSpinnerDevicecompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "mSpinnerDevicecompany/onItemSelected: ");
                if (mMakeDeviceListener != null) {
                    String company = (String) parent.getItemAtPosition(position);
                    mMakeDeviceListener.setDeviceCom(company);
                } else {
                    Log.w(TAG, "onItemSelected: mMakeDeviceListener is null");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerDevicetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "mSpinnerDevicetype/onItemSelected: ");
                String type = (String) parent.getItemAtPosition(position);
                deviceChoosed(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void deviceChoosed(String type) {
        if (mMakeDeviceListener != null) {
            mMakeDeviceListener.setDeviceType(type);
            for (int i = 0; i < Configs.DEVICES_SUPPORTABLE.DEVICETYPE_CHOICES.size(); i++) {
                String item = Configs.DEVICES_SUPPORTABLE.DEVICETYPE_CHOICES.get(i);
                if (TextUtils.equals(item, type)) {
                    mMakeDeviceListener.setDeviceName(
                            Configs.DEVICES_SUPPORTABLE.DEVICENAME_CHOCIED.get(i)
                    );
                    mMakeDeviceListener.setAeName(
                            Configs.DEVICES_SUPPORTABLE.AENAME_CHOICES.get(i)
                    );
                    mMakeDeviceListener.setCntName(
                            Configs.DEVICES_SUPPORTABLE.CNTNAME_CHOICES.get(i)
                    );
                    // TODO: 8/9/17 REPLACE ROOMNAMESTUB
                    mMakeDeviceListener.setRoomName("거실");
                    break;
                }
            }
        } else {
            Log.w(TAG, "onItemSelected: mMakeDeviceListener is null");
        }
    }

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
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.device_adddeviceproductfragment_fragment, container, false);

        mUnbinder = ButterKnife.bind(this, layout);
        initLayout();

        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
