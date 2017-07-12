package com.sm_arts.jibcon.device.adddevice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.sm_arts.jibcon.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDevice0Fragment extends Fragment {
    LinearLayout mLinearLayout;

    @BindView(R.id.addDevice0Spinner1) Spinner mSpinner1;
    @BindView(R.id.addDevice0Spinner2) Spinner mSpinner2;
    @OnClick(R.id.addDeviceBtn) void addDeviceBtn(){
        mMakeDeviceListener.NextPage(1);
    };

    ArrayList<String> mArr1;
    ArrayList<String> mArr2;
    ArrayAdapter<String> mAdapter1;
    ArrayAdapter<String> mAdapter2;
    MakeDeviceListner mMakeDeviceListener;
    String mSelectedCompany;
    String mSelectedDevicename;

    private  void initLayout()
    {
        //제조사 제품 서버로부터 받아야함
        mArr1=new ArrayList<>();
        mArr1.add("삼성");
        mArr1.add("LG");
        mArr1.add("SK");

        mArr2=new ArrayList<>();
        mArr2.add("에어컨");
        mArr2.add("전구");
        mArr2.add("선풍기");
        mArr2.add("냉장고");


        mAdapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,mArr1);
        mAdapter2= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,mArr2);

        mSpinner1.setAdapter(mAdapter1);
        mSpinner2.setAdapter(mAdapter2);

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
                mSelectedDevicename=mArr2.get(position);
                mMakeDeviceListener.setDeviceName(mSelectedDevicename);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMakeDeviceListener=(MakeDeviceListner) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLinearLayout = (LinearLayout)inflater.inflate(R.layout.device_add_device0_activity,container,false);
        ButterKnife.bind(this,mLinearLayout);

        initLayout();
        mMakeDeviceListener.setDeviceCom(mSelectedCompany);
        mMakeDeviceListener.setDeviceName(mSelectedDevicename);


        return mLinearLayout;
    }
}
