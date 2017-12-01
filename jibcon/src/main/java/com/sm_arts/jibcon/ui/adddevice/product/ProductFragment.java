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
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.utils.consts.Configs;
import com.sm_arts.jibcon.utils.housemanager.JibconHouseManager;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017-04-15.
 */

public class ProductFragment extends Fragment {
    private static final String TAG = "AddDeviceProductFragmen";

    @BindView(R.id.spinner_devicecompany)
    Spinner mSpinnerDevicecompany;
    @BindView(R.id.spinner_devicetype)
    Spinner mSpinnerDevicetype;
    @BindView(R.id.btnNext)
    Button mBtnNext;
    String type;
    String company;
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
         v -> mMakeDeviceListener.nextPage(this)
        );

        mSpinnerDevicecompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "mSpinnerDevicecompany/onItemSelected: ");
                if (mMakeDeviceListener != null) {
                    company = (String) parent.getItemAtPosition(position);
                    mMakeDeviceListener.setDeviceCom(company);
                    parent.getSelectedView();
                    TextView tv = (TextView) parent.getSelectedView();
                    tv.setText(company);
                    tv.setTextColor(getResources().getColor(R.color.black_opaque));
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
                type = (String) parent.getItemAtPosition(position);
                deviceChoosed(type);
                TextView tv = (TextView) parent.getSelectedView();
                tv.setText(type);
                tv.setTextColor(getResources().getColor(R.color.black_opaque));
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
//                    mMakeDeviceListener.setAeName(
//                            Configs.DEVICES_SUPPORTABLE.AENAME_CHOICES.get(i)
//                    );
//                    mMakeDeviceListener.setCntName(
//                            Configs.DEVICES_SUPPORTABLE.CNTNAME_CHOICES.get(i)
//                    );
                    mMakeDeviceListener.setUserId(
                            JibconLoginManager.getInstance().getUserId()
                    );
                    // TODO: 8/9/17 REPLACE ROOMNAMESTUB
                    mMakeDeviceListener.setRoomName(JibconHouseManager.getInstance().getmCurrentHouse().houseName);
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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.adddevice_product_fragment, container, false);

        mUnbinder = ButterKnife.bind(this, layout);
        initLayout();

        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mUnbinder.unbind();
    }
}
