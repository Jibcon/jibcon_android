package com.sm_arts.jibcon.Device;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.sm_arts.jibcon.Device.service.DeviceService;
import com.sm_arts.jibcon.Device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuActivity extends Fragment {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    public DeviceMenuActivity(){}

    SwipeRefreshLayout swiperefreshlayout;

    //Button button;
    static myGridView DeviceGridview;
    DeviceMenuAdapter adapter;
    GlobalApplication app;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //init();
//    }

    private View initLayout(LayoutInflater inflater, ViewGroup container)
    {
        View root = inflater.inflate(R.layout.menu_divice,container,false);

        swiperefreshlayout=(SwipeRefreshLayout)root.findViewById(R.id.swipelayout_menu_deivce);

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DeviceServiceImpl.getInstance().reloadDeviceItems(new DeviceService.onSuccessListener() {
                    @Override
                    public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
                        adapter.setDeviceItems((ArrayList)deviceItems);
                        adapter.notifyDataSetChanged();
                        swiperefreshlayout.setRefreshing(false);
                    }
                });
            }
        });

//        button=(Button)root.findViewById(R.id.Btn_getDevices);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { // todo buttonclick -> reload
//                DeviceServiceImpl.getInstance().reloadDeviceItems(new DeviceService.onSuccessListener() {
//                    @Override
//                    public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
//                        adapter.setDeviceItems((ArrayList)deviceItems);
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        });
        DeviceGridview = (myGridView)root.findViewById(R.id.ScrollViewDevice);

        DeviceGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: "+adapter.getDeviceItems().get(position).getDeviceName());
//                Toast.makeText(getActivity().getApplicationContext(),adapter.getDeviceItems().get(position).getDeviceName(),Toast.LENGTH_SHORT).show();
                //
            }
        });
        // TODO: 2017-04-06 그리드뷰에 리스너 달기

        adapter = new DeviceMenuAdapter(getActivity().getApplicationContext());
        adapter.setDeviceItems(new ArrayList<DeviceItem>());
        DeviceGridview.setAdapter(adapter);

        Log.d(TAG, "onCreateView: ");
        DeviceServiceImpl.getInstance().getDeviceItems(new DeviceService.onSuccessListener() {
            @Override
            public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
                Log.d(TAG, "onSuccessGetDeviceItems: ");
                adapter.setDeviceItems((ArrayList)deviceItems);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        app=(GlobalApplication)getActivity().getApplicationContext();


        Log.d("FragmentCheck","DeviceMenuActivity onCreateView");
        View root=initLayout(inflater,container);
        return root;
    }
}
