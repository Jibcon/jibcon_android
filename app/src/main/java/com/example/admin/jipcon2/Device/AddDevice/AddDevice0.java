package com.example.admin.jipcon2.Device.AddDevice;

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

import com.example.admin.jipcon2.R;

import java.util.ArrayList;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDevice0 extends Fragment {
    LinearLayout linearLayout;

    Spinner spinner1;
    Spinner spinner2;
    Button nextButton;
    ArrayList<String> arr1;
    ArrayList<String> arr2;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    MakeDeviceListner makeDeviceListener;
    String selectedCompany;
    String selectedDevicename;

    private  void initLayout()
    {
        //제조사 제품 서버로부터 받아야함
        arr1=new ArrayList<>();
        arr1.add("삼성");
        arr1.add("LG");
        arr1.add("SK");

        arr2=new ArrayList<>();
        arr2.add("에어컨");
        arr2.add("전구");
        arr2.add("선풍기");
        arr2.add("냉장고");


        spinner1=(Spinner)linearLayout.findViewById(R.id.Spinner_adddevice0_1);
        spinner2=(Spinner)linearLayout.findViewById(R.id.Spinner_adddevice0_2);
        adapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arr1);
        adapter2= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arr2);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        nextButton=(Button)linearLayout.findViewById(R.id.Btn_addDevice0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDeviceListener.NextPage(1);
            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCompany = arr1.get(position);
                makeDeviceListener.setDeviceCom(selectedCompany);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDevicename=arr2.get(position);
                makeDeviceListener.setDeviceName(selectedDevicename);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        makeDeviceListener=(MakeDeviceListner) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        linearLayout = (LinearLayout)inflater.inflate(R.layout.add_device0,container,false);

        initLayout();
        makeDeviceListener.setDeviceCom(selectedCompany);
        makeDeviceListener.setDeviceName(selectedDevicename);


        return linearLayout;
    }
}
