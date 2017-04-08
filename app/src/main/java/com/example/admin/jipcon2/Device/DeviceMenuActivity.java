package com.example.admin.jipcon2.Device;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.R;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuActivity extends Fragment {

    public DeviceMenuActivity(){}

    static myGridView DeviceGridview;
    DeviceMenuAdapter adapter;
    GlobalApplication app;



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //init();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        app=(GlobalApplication)getActivity().getApplicationContext();

        Log.d("FragmentCheck","DeviceMenuActivity onCreateView");

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.menu_divice, container, false);

        View root = inflater.inflate(R.layout.menu_divice,container,false);



        DeviceGridview = (myGridView)root.findViewById(R.id.ScrollViewDevice);

        DeviceGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),app.getDeviceItemArrayList().get(position).getItemname(),Toast.LENGTH_SHORT).show();
                //
            }
        });
        // TODO: 2017-04-06 그리드뷰에 리스너 달기

        adapter = new DeviceMenuAdapter(getActivity().getApplicationContext());
        adapter.setDeviceItems(app.getDeviceItemArrayList());

        DeviceGridview.setAdapter(adapter);

        return root;
    }
}
