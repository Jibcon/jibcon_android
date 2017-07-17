package com.sm_arts.jibcon.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.sm_arts.jibcon.device.service.DeviceService;
import com.sm_arts.jibcon.device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.floatingbuttonui.FloatingButtonDeviceActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuFragment extends Fragment {
    private final String TAG = "jibcon/"+getClass().getSimpleName();

    SwipeRefreshLayout mSwiperefreshlayout;

    //Button button;
    static DeviceMenuGridView sDeviceGridview;
    DeviceMenuAdapter mAdapter;
    GlobalApplication mApp;


    private View initLayout(LayoutInflater inflater, ViewGroup container)
    {
            View root = inflater.inflate(R.layout.device_devicemenufragment_fragment,container,false);

        ImageButton fab= (ImageButton)root.findViewById(R.id.fab_device_behind);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(),FloatingButtonDeviceActivity.class));
                //getActivity().finish();
            }
        });
        mSwiperefreshlayout=(SwipeRefreshLayout)root.findViewById(R.id.swipelayout_menu_deivce);

        mSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DeviceServiceImpl.getInstance().reloadDeviceItems(new DeviceService.onSuccessListener() {
                    @Override
                    public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
                        mAdapter.setDeviceItems((ArrayList)deviceItems);
                        mAdapter.notifyDataSetChanged();
                        mSwiperefreshlayout.setRefreshing(false);
                    }
                });
            }
        });

        sDeviceGridview = (DeviceMenuGridView)root.findViewById(R.id.ScrollViewDevice);

        sDeviceGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: "+mAdapter.getDeviceItems().get(position).getDeviceName());
//                Toast.makeText(getActivity().getApplicationContext(),mAdapter.getDeviceItems().get(position).getDeviceName(),Toast.LENGTH_SHORT).show();
                //
            }
        });
        // TODO: 2017-04-06 그리드뷰에 리스너 달기

        mAdapter = new DeviceMenuAdapter(getActivity().getApplicationContext());
        mAdapter.setDeviceItems(new ArrayList<DeviceItem>());
        sDeviceGridview.setAdapter(mAdapter);

        Log.d(TAG, "onCreateView: ");
        DeviceServiceImpl.getInstance().getDeviceItems(new DeviceService.onSuccessListener() {
            @Override
            public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
                Log.d(TAG, "onSuccessGetDeviceItems: ");
                mAdapter.setDeviceItems((ArrayList)deviceItems);
                mAdapter.notifyDataSetChanged();
            }
        });

        //floatingbuttoninit(root,container, inflater);


        return root;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApp=(GlobalApplication)getActivity().getApplicationContext();


        Log.d("FragmentCheck","DeviceMenuFragment onCreateView");
        View root=initLayout(inflater,container);
        return root;
    }

}
