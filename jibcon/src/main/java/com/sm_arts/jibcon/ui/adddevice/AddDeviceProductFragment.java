package com.sm_arts.jibcon.ui.adddevice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.utils.consts.Configs;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDeviceProductFragment extends Fragment {
    LinearLayout mLinearLayout;

    private Spinner mSpinner1;
    private Spinner mSpinner2;
    private Button mNextButton;
    private ArrayList<String> mArr1;
    private ArrayList<String> mArr2;
    private ArrayAdapter<String> mAdapter1;
    private ArrayAdapter<String> mAdapter2;
    private AddDeviceListner mMakeDeviceListener;
    private String mSelectedCompany;
    private String mSelectedDevicename;

    private  void initLayout() {
        //제조사 제품 서버로부터 받아야함
        mArr1 = new ArrayList<>();

        mArr1.add("smArts");

        mArr2 = new ArrayList<>();

        mArr2.add("BULB");
        mArr2.add("SENSOR");

//        mArr2.add("에어컨");
//        mArr2.add("전구");
//        mArr2.add("선풍기");
//        mArr2.add("냉장고");

        mSpinner1 = (Spinner) mLinearLayout.findViewById(R.id.Spinner_adddevice0_1);
        mSpinner2 = (Spinner) mLinearLayout.findViewById(R.id.Spinner_adddevice0_2);

        mAdapter1 = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, mArr1
        );
        mAdapter2 = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, mArr2
        );

        mSpinner1.setAdapter(mAdapter1);
        mSpinner2.setAdapter(mAdapter2);


        mNextButton = (Button) mLinearLayout.findViewById(R.id.Btn_addDevice0);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMakeDeviceListener.nextPage(1);
            }
        });

        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedCompany = mArr1.get(position);


                mMakeDeviceListener.setDeviceCom(mSelectedCompany);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedDevicename = mArr2.get(position);
                setDeviceConf(mSelectedDevicename);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setDeviceConf(String mSelectedDevicename) {

        // TODO: 2017-08-08 센서 종류대로 정보 넣기 mMakeDeviceListener.method()
        if(mSelectedDevicename == Configs.DEVICES_SUPPORTABLE.DEVICETYPE_CHOICES.BULB) {

        }
        else if(mSelectedDevicename == Configs.DEVICES_SUPPORTABLE.DEVICETYPE_CHOICES.SENSOR) {

        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMakeDeviceListener = (AddDeviceListner) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLinearLayout = (LinearLayout)inflater.inflate(R.layout.device_adddeviceproductfragment_fragment, container, false);

        initLayout();
        mMakeDeviceListener.setDeviceCom(mSelectedCompany);
        mMakeDeviceListener.setDeviceName(mSelectedDevicename);

        return mLinearLayout;
    }
}
